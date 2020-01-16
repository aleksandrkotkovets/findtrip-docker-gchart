package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.OrderDTO;
import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.exception.OrderOnThisFlightAlreadyExistException;
import by.sam_solutions.findtrip.exception.OrderSeatsException;
import by.sam_solutions.findtrip.exception.WalletBalanceException;
import by.sam_solutions.findtrip.exception.WalletIncorrectBalanceException;
import by.sam_solutions.findtrip.repository.*;
import by.sam_solutions.findtrip.repository.entity.*;
import by.sam_solutions.findtrip.service.FlightService;
import by.sam_solutions.findtrip.service.OrderService;
import by.sam_solutions.findtrip.service.PaymentService;
import by.sam_solutions.findtrip.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private WalletRepository walletRepository;

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    /**
     * Send message on email
     */
    @Transactional
    @Override
    public void add(OrderCreateUpdateDTO orderDTO) {
        if (orderRepository.getOrderIdByFlightIdAndUserId(orderDTO.getIdFlight(), orderDTO.getIdClient()) != null) {
            throw new OrderOnThisFlightAlreadyExistException("Order on this flight already exist", orderDTO);
        }

        FlightEntity flightEntity = flightRepository.findById(orderDTO.getIdFlight()).get();

        if (orderDTO.getCountSeats() > flightEntity.getFreeSeats()) {
            throw new OrderSeatsException("The number of free seats per flight is less than what you choose");
        }

        if (flightEntity.getFreeSeats() == 0) {
            throw new OrderSeatsException("No empty seats");
        }

        UserEntity userEntity = userRepository.findById(orderDTO.getIdClient()).get();
        if (paymentService.payOrder(orderDTO, flightEntity, userEntity)) {
            Double finalCost = Double.parseDouble(decimalFormat.format(orderDTO.getCountSeats() * flightEntity.getPrice()).replace(',', '.'));
            OrderEntity orderEntity = orderRepository.save(new OrderEntity(finalCost, new Timestamp(new Date().getTime()), userEntity, flightEntity));
            for (int i = 0; i < orderDTO.getCountSeats(); i++) {
                ticketRepository.save(new TicketEntity(orderDTO.getPriceOneSeat(), orderEntity));

            }

            flightEntity.setFreeSeats(flightEntity.getFreeSeats() - orderDTO.getCountSeats());
            flightRepository.save(flightEntity);
        }
    }

    /*@Transactional
    @Override
    public boolean payOrder(OrderCreateUpdateDTO orderDTO, FlightEntity flightEntity, UserEntity userEntity) {
        WalletEntity walletEntity = walletRepository.findByOwnerId(userEntity.getId());
        Double mustPay = orderDTO.getCountSeats() * flightEntity.getPrice();
        Double currBalance = walletEntity.getSum();
        if (mustPay < currBalance) {
            currBalance -= mustPay;
            walletEntity.setSum(Double.parseDouble(decimalFormat.format(currBalance).replace(",", ".")));
            walletRepository.save(walletEntity);
        } else {
            throw new WalletIncorrectBalanceException("You need to replenish wallet balance.\nYour balance: " + walletEntity.getSum());
        }
        return true;
    }*/

    @Override
    public List<OrderDTO> getOrdersByUserId(Long id) {
        return mapOrderDTOList(orderRepository.findAllByUserId(id));
    }

    @Override
    public OrderDTO findById(Long id) {
        return mapOrderDTO(orderRepository.findById(id).get());
    }


    @Override
    @Transactional
    public void deleteTicketsOnFlightByUSer(OrderCreateUpdateDTO order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId()).get();
        FlightEntity flightEntity = orderEntity.getFlight();
        Integer currFreeSeats = flightEntity.getFreeSeats();

        if (order.getReturnTickets() > orderEntity.getTickets().size() || order.getReturnTickets() == 0) {
            throw new OrderSeatsException("Incorrect seats number.\nYou want return:" + order.getReturnTickets() + "\nMax return only:" + orderEntity.getTickets().size());
        } else if (order.getReturnTickets() == orderEntity.getTickets().size()) {
            if(paymentService.returnMoney(order,flightEntity, userRepository.findById(order.getIdClient()).get())) {
                orderRepository.deleteById(order.getId());
            }
        } else {
            if(paymentService.returnMoney(order,flightEntity, userRepository.findById(order.getIdClient()).get())) {
                List<TicketEntity> ticketEntityList = ticketRepository.findAllByOrderId(order.getId());
                for (int i = 0; i < order.getReturnTickets(); i++) {
                    ticketRepository.deleteById(ticketEntityList.get(i).getId());
                }

                Double mustReturn = order.getReturnTickets() * flightEntity.getPrice();

                orderEntity.setFinalCost(Double.parseDouble(decimalFormat
                                                                   .format(orderEntity.getFinalCost()-mustReturn)
                                                                   .replace(',','.')));

                orderRepository.save(orderEntity);
            }
        }

        flightEntity.setFreeSeats(currFreeSeats + order.getReturnTickets());
        flightRepository.save(flightEntity);

    }

    @Transactional
    @Override
    public void takeMoreTickets(OrderCreateUpdateDTO order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId()).get();
        Integer currTickets = orderEntity.getTickets().size();

        if (orderEntity.getFlight().getFreeSeats() == 0) {
            throw new OrderSeatsException("No empty seats");
        }

        if (order.getCountSeats() > 0 && (order.getCountSeats() <= orderEntity.getFlight().getFreeSeats())) {

            FlightEntity flightEntity = orderEntity.getFlight();

            if (paymentService.payOrder(order,flightEntity, userRepository.findById(order.getIdClient()).get() )) {

                for (int i = 0; i < order.getCountSeats(); i++) {
                    ticketRepository.save(new TicketEntity(order.getPriceOneSeat(), orderEntity));
                }

                currTickets += order.getCountSeats();

                flightEntity.setFreeSeats(flightEntity.getFreeSeats() - order.getCountSeats());
                orderEntity.setFinalCost(Double.parseDouble(decimalFormat.format(currTickets * flightEntity.getPrice()).replace(',', '.')));

                orderRepository.save(orderEntity);
                flightRepository.save(flightEntity);
            }

        } else {
            throw new OrderSeatsException("Incorrect seats number");
        }

    }

    private List<OrderDTO> mapOrderDTOList(List<OrderEntity> orderEntityList) {
        return orderEntityList.stream()
                .map(a -> new OrderDTO(
                        a.getId(),
                        a.getFinalCost(),
                        a.getOrderDate(),
                        new UserDTO(
                                a.getUser().getLogin(),
                                a.getUser().getPassword(),
                                a.getUser().getEmail(),
                                a.getUser().getFirstName(),
                                a.getUser().getLastName(),
                                a.getUser().getPatronymic(),
                                a.getUser().getPhoneNumber()
                        ),
                        flightService.mapFlightDTO(a.getFlight()),
                        ticketService.mapTicketDTOList(a.getTickets())

                ))
                .collect(Collectors.toList());
    }

    private OrderDTO mapOrderDTO(OrderEntity orderEntity) {
        return new OrderDTO(orderEntity.getId(),
                orderEntity.getFinalCost(),
                orderEntity.getOrderDate(),
                new UserDTO(
                        orderEntity.getUser().getLogin(),
                        orderEntity.getUser().getPassword(),
                        orderEntity.getUser().getEmail(),
                        orderEntity.getUser().getFirstName(),
                        orderEntity.getUser().getLastName(),
                        orderEntity.getUser().getPatronymic(),
                        orderEntity.getUser().getPhoneNumber()
                ),
                flightService.mapFlightDTO(orderEntity.getFlight()),
                ticketService.mapTicketDTOList(orderEntity.getTickets()));
    }


}
