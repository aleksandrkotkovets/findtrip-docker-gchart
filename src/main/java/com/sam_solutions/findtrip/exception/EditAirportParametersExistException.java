package com.sam_solutions.findtrip.exception;

import com.sam_solutions.findtrip.controller.dto.AirportDTO;

public class EditAirportParametersExistException extends RuntimeException {

    AirportDTO airportDTO;

    public EditAirportParametersExistException() {
    }

    public EditAirportParametersExistException(String message, AirportDTO airportDTO) {
        super(message);
        this.airportDTO = airportDTO;
    }

    public EditAirportParametersExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EditAirportParametersExistException(Throwable cause) {
        super(cause);
    }

}
