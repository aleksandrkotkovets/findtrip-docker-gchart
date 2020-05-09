package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    List<AirportDTO> findAll();

    AirportDTO findById(Long id);

    void updateAirport(AirportDTO airportDTO);

    void delete(Long id);

    void save(AirportDTO airportDTO);
}
