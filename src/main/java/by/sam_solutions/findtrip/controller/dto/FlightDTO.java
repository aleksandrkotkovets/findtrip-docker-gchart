package by.sam_solutions.findtrip.controller.dto;

import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


public class FlightDTO {

    private  Long id;

    @NotNull
    private Integer freeSeats;

    @NotNull
    private Integer allSeats;

    @NotNull
    private Double price;

    @NotNull
    private Timestamp departureDate;

    @NotNull
    private Timestamp arrivalDate;

//    private Set<TicketDTO> tickets;

    private PlaneDTO plane;

    private AirportDTO airportDeparture;

    private AirportDTO airportArrival;


}
