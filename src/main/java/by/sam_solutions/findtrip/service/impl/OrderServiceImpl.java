package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.OrderDTO;
import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.exception.OrderOnThisFlightAlreadyExistException;
import by.sam_solutions.findtrip.exception.OrderSeatsException;
import by.sam_solutions.findtrip.repository.FlightRepository;
import by.sam_solutions.findtrip.repository.OrderRepository;
import by.sam_solutions.findtrip.repository.TicketRepository;
import by.sam_solutions.findtrip.repository.UserRepository;
import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import by.sam_solutions.findtrip.repository.entity.OrderEntity;
import by.sam_solutions.findtrip.repository.entity.TicketEntity;
import by.sam_solutions.findtrip.repository.entity.UserEntity;
import by.sam_solutions.findtrip.service.FlightService;
import by.sam_solutions.findtrip.service.OrderService;
import by.sam_solutions.findtrip.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    TicketService ticketService;

    @Override
    public void add(OrderCreateUpdateDTO orderDTO) {

        if (orderRepository.getOrderIdByFlightIdAndUserId(orderDTO.getIdFlight(), orderDTO.getIdClient()) != null) {
            throw new OrderOnThisFlightAlreadyExistException("Order on this flight already exist", orderDTO);
        }

        FlightEntity flightEntity = flightRepository.findById(orderDTO.getIdFlight()).get();

        if (orderDTO.getCountSeats() > flightEntity.getFreeSeats()) {
            throw new OrderSeatsException("The number of free seats per flight is less than what you chose");
        }

        if (flightEntity.getFreeSeats() == 0) {
            throw new OrderSeatsException("No empty seats");
        }

        UserEntity userEntity = userRepository.findById(orderDTO.getIdClient()).get();


        OrderEntity orderEntity = orderRepository.save(new OrderEntity(orderDTO.getFinalCost(), new Timestamp(new Date().getTime()), userEntity, flightEntity));
        for (int i = 0; i < orderDTO.getCountSeats(); i++) {
            ticketRepository.save(new TicketEntity(orderDTO.getPriceOneSeat(), orderEntity));

        }

        flightEntity.setFreeSeats(flightEntity.getFreeSeats() - orderDTO.getCountSeats());
        flightRepository.save(flightEntity);
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
        OrderEntity orderEntity = orderRepository.findById(order.getId()).get();
        FlightEntity flightEntity = orderEntity.getFlight();
        Integer currFreeSeats = flightEntity.getFreeSeats();

        if (order.getReturnTickets() > orderEntity.getTickets().size() || order.getReturnTickets() == 0) {
            throw new OrderSeatsException("Incorrect seats number.\nYou want return:" + order.getReturnTickets() + "\nMax return only:" + orderEntity.getTickets().size());
        } else if (order.getReturnTickets() == orderEntity.getTickets().size()) {
            orderRepository.deleteById(order.getId());
        } else {
            List<TicketEntity> ticketEntityList = ticketRepository.findAllByOrderId(order.getId());
            for (int i = 0; i < order.getReturnTickets(); i++) {
                ticketRepository.deleteById(ticketEntityList.get(i).getId());
            }
        }

        flightEntity.setFreeSeats(currFreeSeats + order.getReturnTickets());
        flightRepository.save(flightEntity);

    }

    @Override
    public void editCountTickets(OrderCreateUpdateDTO order) {
        OrderEntity orderEntity = orderRepository.findById(order.getId()).get();

        if (orderEntity.getFlight().getFreeSeats() == 0) {
            throw new OrderSeatsException("No empty seats");
        }

        if (order.getCountSeats() > 0 && (order.getCountSeats() <= orderEntity.getFlight().getFreeSeats())) {

            for (int i = 0; i < order.getCountSeats(); i++) {
                ticketRepository.save(new TicketEntity(order.getPriceOneSeat(), orderEntity));
            }

            FlightEntity flightEntity = orderEntity.getFlight();
            flightEntity.setFreeSeats(flightEntity.getFreeSeats() - order.getCountSeats());
            flightRepository.save(flightEntity);

        } else {
            throw new OrderSeatsException("Incorrect seats number");
        }

    }

    public List<OrderDTO> mapOrderDTOList(List<OrderEntity> orderEntityList) {
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

    public OrderDTO mapOrderDTO(OrderEntity orderEntity) {
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
