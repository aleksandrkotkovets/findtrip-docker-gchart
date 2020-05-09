package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.TicketDTO;
import com.sam_solutions.findtrip.repository.entity.TicketEntity;

import java.util.List;

public interface TicketService {

    List<TicketDTO> mapTicketDTOList(List<TicketEntity> tickets);
}
