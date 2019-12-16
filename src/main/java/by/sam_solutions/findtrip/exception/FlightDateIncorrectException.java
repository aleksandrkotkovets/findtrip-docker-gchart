package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;

public class FlightDateIncorrectException extends RuntimeException {

    FlightCreateUpdateDTO flightDTO;

    public FlightDateIncorrectException(String msg, FlightCreateUpdateDTO flightDTO) {
        super(msg);
        this.flightDTO = flightDTO;
    }


}
