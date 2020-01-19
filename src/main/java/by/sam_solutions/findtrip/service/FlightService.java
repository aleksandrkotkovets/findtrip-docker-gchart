package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.FlightDTO;
import by.sam_solutions.findtrip.controller.dto.OrderDTO;
import by.sam_solutions.findtrip.repository.entity.FlightEntity;

import java.text.ParseException;
import java.util.List;

public interface FlightService {
    void addFlight(FlightCreateUpdateDTO flightDTO);

    FlightDTO getById(Long id);

    void edit(FlightCreateUpdateDTO flightDTO);

    List<FlightDTO> findAll();

    List<FlightDTO> findFlightsByCriteria(Long idCityDeparture, Long idCityArrival, String dateDeparture) throws ParseException;

    FlightDTO mapFlightDTO(FlightEntity flight);

    Integer getNumberSoldTicketById(Long id);

    void canceledFlight(Long idFlight);


}
