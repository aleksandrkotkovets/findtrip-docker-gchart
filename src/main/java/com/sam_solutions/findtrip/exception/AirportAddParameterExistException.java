package com.sam_solutions.findtrip.exception;

import com.sam_solutions.findtrip.controller.dto.AirportDTO;

public class AirportAddParameterExistException extends RuntimeException {

    public AirportDTO airportDTO;

    public AirportAddParameterExistException(String message, AirportDTO airportDTO) {
        super(message);
        this.airportDTO = airportDTO;
    }
}
