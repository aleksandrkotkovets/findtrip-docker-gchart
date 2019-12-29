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

import java.sql.Timestamp;
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
            throw new OrderSeatsException("The number of free seats per flight is less than what you chose", orderDTO);
        }

        if (flightEntity.getFreeSeats() == 0) {
            throw new OrderSeatsException("No empty seats", orderDTO);
        }

        UserEntity userEntity = userRepository.findById(orderDTO.getIdClient()).get();
        Integer currFreeSeats = flightEntity.getFreeSeats();

        OrderEntity orderEntity = orderRepository.save(new OrderEntity(orderDTO.getFinalCost(), new Timestamp(new Date().getTime()), userEntity, flightEntity));

        for (int i = 0; i < orderDTO.getCountSeats(); i++) {
            ticketRepository.save(new TicketEntity(orderDTO.getPriceOneSeat(), orderEntity));
            currFreeSeats--;
        }

        flightEntity.setFreeSeats(currFreeSeats);
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
    public OrderCreateUpdateDTO deleteTicketsOnFlightByUSer(OrderCreateUpdateDTO order) {
        return null;
    }

    public List<OrderDTO> mapOrderDTOList(List<OrderEntity> orderEntityList) {
        return orderEntityList.stream()
                .map(a->new OrderDTO(
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

    public OrderDTO mapOrderDTO(OrderEntity orderEntity){
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
