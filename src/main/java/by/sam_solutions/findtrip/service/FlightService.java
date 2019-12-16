package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;

public interface FlightService {
    void addFlight(FlightCreateUpdateDTO flightDTO);
}
