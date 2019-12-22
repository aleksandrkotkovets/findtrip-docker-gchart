package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.TicketCreateUpdateDTO;

public class TicketOnThisFlightAlreadyExistException extends RuntimeException {

    public TicketOnThisFlightAlreadyExistException(String msg, TicketCreateUpdateDTO ticketDTO) {
    }

}
