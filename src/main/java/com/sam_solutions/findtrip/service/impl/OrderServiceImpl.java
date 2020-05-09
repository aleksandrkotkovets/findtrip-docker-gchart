package com.sam_solutions.findtrip.service.impl;

import com.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import com.sam_solutions.findtrip.controller.dto.OrderDTO;
import com.sam_solutions.findtrip.controller.dto.UserDTO;
import com.sam_solutions.findtrip.exception.OrderOnThisFlightAlreadyExistException;
import com.sam_solutions.findtrip.exception.OrderSeatsException;
import com.sam_solutions.findtrip.repository.FlightRepository;
import com.sam_solutions.findtrip.repository.OrderRepository;
import com.sam_solutions.findtrip.repository.TicketRepository;
import com.sam_solutions.findtrip.repository.UserRepository;
import com.sam_solutions.findtrip.repository.entity.*;
import com.sam_solutions.findtrip.repository.entity.FlightEntity;
import com.sam_solutions.findtrip.repository.entity.OrderEntity;
import com.sam_solutions.findtrip.repository.entity.OrderStatus;
import com.sam_solutions.findtrip.repository.entity.TicketEntity;
import com.sam_solutions.findtrip.repository.entity.UserEntity;
import com.sam_solutions.findtrip.service.FlightService;
import com.sam_solutions.findtrip.service.OrderService;
import com.sam_solutions.findtrip.service.PaymentService;
import com.sam_solutions.findtrip.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private final static Logger LOGGER = LogManager.getLogger();

    private FlightRepository flightRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private TicketRepository ticketRepository;
    private FlightService flightService;
    private TicketService ticketService;
    private PaymentService paymentService;

    @Autowired
    public OrderServiceImpl(FlightRepository flightRepository,
                            UserRepository userRepository,
                            OrderRepository orderRepository,
                            TicketRepository ticketRepository,
                            FlightService flightService,
                            TicketService ticketService,
                            PaymentService paymentService) {
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.ticketRepository = ticketRepository;
        this.flightService = flightService;
        this.ticketService = ticketService;
        this.paymentService = paymentService;
    }

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");


    @Transactional
    @Override
    public OrderDTO add(OrderCreateUpdateDTO orderDTO) {
        LOGGER.info("Add order: "+orderDTO );
        OrderDTO savedOrder = new OrderDTO();

        if (orderRepository.getOrderIdByFlightIdAndUserId(orderDTO.getIdFlight(), orderDTO.getIdClient()) != null) {
            LOGGER.error("Order on this flight already exist");
            throw new OrderOnThisFlightAlreadyExistException("Order on this flight already exist", orderDTO);
        }

        FlightEntity flightEntity = flightRepository.findById(orderDTO.getIdFlight()).get();


        if (orderDTO.getCountSeats() > flightEntity.getFreeSeats()) {
            LOGGER.error("The number of free seats per flight is less than what you choose");
            throw new OrderSeatsException("The number of free seats per flight is less than what you choose");
        }

        if (orderDTO.getCountSeats() == 0) {
            LOGGER.error("Count seats = 0");
            throw new OrderSeatsException("Count seats = 0");
        }

        if (flightEntity.getFreeSeats() == 0) {
            LOGGER.error("No empty seats");
            throw new OrderSeatsException("No empty seats");
        }

        UserEntity userEntity = userRepository.findById(orderDTO.getIdClient()).get();
        if (paymentService.payOrder(orderDTO, flightEntity, userEntity)) {
            Double finalCost = Double.parseDouble(decimalFormat.format(orderDTO.getCountSeats() * flightEntity.getPrice()).replace(',', '.'));
            OrderEntity orderEntity = orderRepository.save(new OrderEntity(finalCost, OrderStatus.ACTIVE, new Timestamp(new Date().getTime()), userEntity, flightEntity));
            List<TicketEntity> savedTickets = new ArrayList<>();
            for (int i = 0; i < orderDTO.getCountSeats(); i++) {
                TicketEntity savedTicket = ticketRepository.save(new TicketEntity(orderDTO.getPriceOneSeat(), orderEntity));
                savedTickets.add(savedTicket);
            }

            flightEntity.setFreeSeats(flightEntity.getFreeSeats() - orderDTO.getCountSeats());
            flightRepository.save(flightEntity);

            orderEntity.setTickets(savedTickets);
            return mapOrderDTO(orderEntity);
        }
        return savedOrder;
    }

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
        LOGGER.info("delete Tickets On Flight where order: " + order);
        OrderEntity orderEntity = orderRepository.findById(order.getId()).get();
        FlightEntity flightEntity = orderEntity.getFlight();
        Integer currFreeSeats = flightEntity.getFreeSeats();

        if (order.getReturnTickets() > orderEntity.getTickets().size() || order.getReturnTickets() == 0) {
            LOGGER.error("Incorrect seats number.\nYou want return:" + order.getReturnTickets() + "\nMax return only:" + orderEntity.getTickets().size());
            throw new OrderSeatsException("Incorrect seats number.\nYou want return:" + order.getReturnTickets() + "\nMax return only:" + orderEntity.getTickets().size());
        } else if (order.getReturnTickets() == orderEntity.getTickets().size()) {
            if (paymentService.returnMoney(order, flightEntity, userRepository.findById(order.getIdClient()).get())) {
                orderRepository.deleteById(order.getId());
            }
        } else {
            if (paymentService.returnMoney(order, flightEntity, userRepository.findById(order.getIdClient()).get())) {
                List<TicketEntity> ticketEntityList = ticketRepository.findAllByOrderId(order.getId());
                for (int i = 0; i < order.getReturnTickets(); i++) {
                    ticketRepository.deleteById(ticketEntityList.get(i).getId());
                }

                Double mustReturn = order.getReturnTickets() * flightEntity.getPrice();

                orderEntity.setFinalCost(Double.parseDouble(decimalFormat
                        .format(orderEntity.getFinalCost() - mustReturn)
                        .replace(',', '.')));

                orderRepository.save(orderEntity);
            }
        }

        flightEntity.setFreeSeats(currFreeSeats + order.getReturnTickets());
        flightRepository.save(flightEntity);

    }

    @Transactional
    @Override
    public OrderDTO takeMoreTickets(OrderCreateUpdateDTO order) {
        OrderDTO savedOrderDTO = null;
        OrderEntity orderEntity = orderRepository.findById(order.getId()).get();
        Integer currTickets = orderEntity.getTickets().size();

        if (orderEntity.getFlight().getFreeSeats() == 0) {
            throw new OrderSeatsException("No empty seats");
        }

        if (order.getCountSeats() > 0 && (order.getCountSeats() <= orderEntity.getFlight().getFreeSeats())) {

            FlightEntity flightEntity = orderEntity.getFlight();

            if (paymentService.payOrder(order, flightEntity, userRepository.findById(order.getIdClient()).get())) {

                for (int i = 0; i < order.getCountSeats(); i++) {
                    ticketRepository.save(new TicketEntity(order.getPriceOneSeat(), orderEntity));
                }

                currTickets += order.getCountSeats();

                flightEntity.setFreeSeats(flightEntity.getFreeSeats() - order.getCountSeats());
                orderEntity.setFinalCost(Double.parseDouble(decimalFormat.format(currTickets * flightEntity.getPrice()).replace(',', '.')));
                orderEntity.setFlight(flightEntity);
                orderEntity.setTickets(ticketRepository.findAllByOrderId(orderEntity.getId()));

                orderRepository.save(orderEntity);
                flightRepository.save(flightEntity);

                savedOrderDTO = mapOrderDTO(orderEntity);
            }

        } else {
            throw new OrderSeatsException("Incorrect seats number");
        }
        return savedOrderDTO;
    }

    @Override
    public List<OrderDTO> findAllByFlightId(Long idFlight) {
        return mapOrderDTOList(orderRepository.findAllByFlightId(idFlight));
    }

    @Override
    public List<OrderDTO> getOrdersByUserIdAndStatus(Long id, OrderStatus status) {
        return mapOrderDTOList(orderRepository.findAllByUserIdAndStatus(id, status, Sort.by("orderDate").ascending()));
    }


    private List<OrderDTO> mapOrderDTOList(List<OrderEntity> orderEntityList) {
        return orderEntityList.stream()
                .map(a -> new OrderDTO(
                        a.getId(),
                        a.getFinalCost(),
                        a.getStatus(),
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
                orderEntity.getStatus(),
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
