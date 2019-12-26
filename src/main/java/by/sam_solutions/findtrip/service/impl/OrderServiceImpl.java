package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
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
import by.sam_solutions.findtrip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

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

    @Override
    public void add(OrderCreateUpdateDTO orderDTO) {

        if (orderRepository.getOrderIdByFlightIdAndUserId(orderDTO.getIdFlight(), orderDTO.getIdClient()) != null) {
            throw new OrderOnThisFlightAlreadyExistException("Order_on_this_flight_already_exist", orderDTO);
        }

        FlightEntity flightEntity = flightRepository.findById(orderDTO.getIdFlight()).get();

        if (orderDTO.getCountSeats() > flightEntity.getFreeSeats()) {
            throw new OrderSeatsException("The_number_of_free_seats_per_flight_is_less_than_what_you_chose", orderDTO);
        }

        if (flightEntity.getFreeSeats() == 0) {
            throw new OrderSeatsException("No_empty_seats", orderDTO);
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
}
