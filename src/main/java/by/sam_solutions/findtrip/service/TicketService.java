package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.TicketDTO;
import by.sam_solutions.findtrip.repository.entity.TicketEntity;

import java.util.List;

public interface TicketService {

    List<TicketDTO> mapTicketDTOList(List<TicketEntity> tickets);
}
