package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.OrderDTO;

public interface EmailSender {
    void sendСonfirmPurchaseToEmail(OrderDTO orderDTO);

    void sendСancellationСonfirmToEmail(OrderDTO orderDTO);
}
