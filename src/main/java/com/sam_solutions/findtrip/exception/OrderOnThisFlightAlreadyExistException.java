package com.sam_solutions.findtrip.exception;

import com.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;

public class OrderOnThisFlightAlreadyExistException extends RuntimeException {
    OrderCreateUpdateDTO orderDTO;

    public OrderOnThisFlightAlreadyExistException(String msg, OrderCreateUpdateDTO orderDTO) {
        super(msg);
        this.orderDTO = orderDTO;
    }
}
