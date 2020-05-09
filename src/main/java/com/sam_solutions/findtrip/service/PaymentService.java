package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import com.sam_solutions.findtrip.repository.entity.FlightEntity;
import com.sam_solutions.findtrip.repository.entity.UserEntity;

public interface PaymentService {


    boolean payOrder(OrderCreateUpdateDTO orderDTO, FlightEntity flightEntity, UserEntity userEntity);

    boolean returnMoney(OrderCreateUpdateDTO order, FlightEntity flightEntity, UserEntity userEntity);

    boolean returnMoneyForFlightCancellation(FlightEntity flightEntity);

}
