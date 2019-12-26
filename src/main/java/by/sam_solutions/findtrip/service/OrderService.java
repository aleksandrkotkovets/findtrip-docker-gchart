package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.TicketCreateUpdateDTO;

public interface OrderService {

    void add(OrderCreateUpdateDTO orderDTO);
}
