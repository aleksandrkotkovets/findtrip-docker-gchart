package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;

public class OrderSeatsException extends RuntimeException {

    public OrderSeatsException(String msg, OrderCreateUpdateDTO orderDTO) {
    }

}
