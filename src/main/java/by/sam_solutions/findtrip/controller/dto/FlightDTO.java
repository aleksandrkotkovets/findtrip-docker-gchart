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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public Integer getAllSeats() {
        return allSeats;
    }

    public void setAllSeats(Integer allSeats) {
        this.allSeats = allSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public PlaneDTO getPlane() {
        return plane;
    }

    public void setPlane(PlaneDTO plane) {
        this.plane = plane;
    }

    public AirportDTO getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(AirportDTO airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public AirportDTO getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(AirportDTO airportArrival) {
        this.airportArrival = airportArrival;
    }
}
