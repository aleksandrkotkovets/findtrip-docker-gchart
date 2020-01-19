package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import by.sam_solutions.findtrip.repository.entity.OrderEntity;
import by.sam_solutions.findtrip.repository.entity.UserEntity;
import by.sam_solutions.findtrip.repository.entity.WalletEntity;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentService {


    boolean payOrder(OrderCreateUpdateDTO orderDTO, FlightEntity flightEntity, UserEntity userEntity);

    boolean returnMoney(OrderCreateUpdateDTO order, FlightEntity flightEntity, UserEntity userEntity);

    boolean returnMoneyForFlightCancellation(FlightEntity flightEntity);

}
