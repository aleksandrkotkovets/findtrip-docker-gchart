package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.OrderDTO;

public interface EmailSender {
    void sendСonfirmPurchaseToEmail(OrderDTO orderDTO);

    void sendСancellationСonfirmToEmail(OrderDTO orderDTO);
}
