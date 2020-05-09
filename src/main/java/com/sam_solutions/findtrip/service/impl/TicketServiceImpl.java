package com.sam_solutions.findtrip.service.impl;

import com.sam_solutions.findtrip.controller.dto.OrderDTO;
import com.sam_solutions.findtrip.controller.dto.TicketDTO;
import com.sam_solutions.findtrip.repository.entity.TicketEntity;
import com.sam_solutions.findtrip.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public List<TicketDTO> mapTicketDTOList(List<TicketEntity> tickets) {
        return tickets.stream().map(a -> new TicketDTO(
                a.getId(),
                new OrderDTO(a.getOrder().getId()),
                a.getPrice()))
                .collect(Collectors.toList());
    }
}

