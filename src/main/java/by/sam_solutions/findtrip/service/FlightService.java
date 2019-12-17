package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.FlightDTO;

public interface FlightService {
    void addFlight(FlightCreateUpdateDTO flightDTO);

    FlightDTO getById(Long id);

    void edit(FlightCreateUpdateDTO flightDTO);
}
