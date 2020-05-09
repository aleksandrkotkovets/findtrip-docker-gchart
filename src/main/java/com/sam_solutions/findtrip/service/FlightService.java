package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;
import com.sam_solutions.findtrip.controller.dto.FlightCriteriaDTO;
import com.sam_solutions.findtrip.controller.dto.FlightDTO;
import com.sam_solutions.findtrip.repository.entity.FlightEntity;
import com.sam_solutions.findtrip.repository.entity.FlightStatus;

import java.text.ParseException;
import java.util.List;

public interface FlightService {
    void addFlight(FlightCreateUpdateDTO flightDTO);

    FlightDTO getById(Long id);

    void edit(FlightCreateUpdateDTO flightDTO);

    List<FlightDTO> findAll();

    List<FlightDTO> findFlightsByCriteria(FlightCriteriaDTO flightCriteriaDTO) throws ParseException;

    Integer getNumberSoldTicketById(Long id);

    void canceledFlight(Long idFlight);

    FlightDTO mapFlightDTO(FlightEntity flightEntity);

    List<FlightDTO> mapListFlightDTO(List<FlightEntity> flightEntityList);

    List<FlightDTO> findAllByStatus(FlightStatus status);
}
