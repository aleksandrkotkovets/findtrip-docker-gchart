package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.TicketCreateUpdateDTO;
import by.sam_solutions.findtrip.exception.TicketOnThisFlightAlreadyExistException;
import by.sam_solutions.findtrip.repository.FlightRepository;
import by.sam_solutions.findtrip.repository.TicketRepository;
import by.sam_solutions.findtrip.repository.UserRepository;
import by.sam_solutions.findtrip.repository.entity.TicketEntity;
import by.sam_solutions.findtrip.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void add(TicketCreateUpdateDTO ticketDTO) {
        TicketEntity ticketEntity = new TicketEntity();

        if(ticketRepository.getTicketIdByFlightIdAndUserId(ticketDTO.getIdFlight(),ticketDTO.getIdClient())!= null){
            throw new TicketOnThisFlightAlreadyExistException("Ticket_on_this_flight_already_exist",ticketDTO);
        }

        ticketEntity.setOrderDate(new Timestamp(new Date().getTime()));
        ticketEntity.setSeat(ticketDTO.getCountSeats());
        ticketEntity.setFinalCost(ticketDTO.getFinalCost());
        ticketEntity.setFlight(flightRepository.getOne(ticketDTO.getIdFlight()));
        ticketEntity.setOwner(userRepository.getOne(ticketDTO.getIdClient()));
        ticketRepository.save(ticketEntity);
    }
}
