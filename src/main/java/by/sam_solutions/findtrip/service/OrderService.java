package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.OrderDTO;
import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import by.sam_solutions.findtrip.repository.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    void add(OrderCreateUpdateDTO orderDTO);

    /*@Transactional
    boolean payOrder(OrderCreateUpdateDTO orderDTO, FlightEntity flightEntity, UserEntity userEntity);
*/
    List<OrderDTO> getOrdersByUserId(Long id);

    OrderDTO findById(Long id);

    void deleteTicketsOnFlightByUSer(OrderCreateUpdateDTO order);

    void takeMoreTickets(OrderCreateUpdateDTO order);
}
