package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import by.sam_solutions.findtrip.repository.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentService {

    @Transactional
    boolean payOrder(OrderCreateUpdateDTO orderDTO, FlightEntity flightEntity, UserEntity userEntity);

    @Transactional
    boolean returnMoney(OrderCreateUpdateDTO order, FlightEntity flightEntity, UserEntity userEntity);
}
