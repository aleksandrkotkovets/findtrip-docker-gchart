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

    private String timeDeparture;

    private String dateDeparture;

    private String timeArrival;

    private String dateArrival;

    @NotNull
    private Timestamp arrivalDate;

    private String travelTime;

    private Integer soldTickets;

    private PlaneDTO plane;

    private AirportDTO airportDeparture;

    private AirportDTO airportArrival;

    public FlightDTO(Long id, Integer freeSeats, Integer allSeats, Double price, Timestamp departureDate, Timestamp arrivalDate,Integer soldTickets ,PlaneDTO planeDTO, AirportDTO airportDeparture, AirportDTO airportArrival) {
            this.id = id;
            this.freeSeats  = freeSeats;
            this.allSeats = allSeats;
            this.price = price;
            this.departureDate = departureDate;
            this.arrivalDate = arrivalDate;
            this.plane = planeDTO;
            this.airportDeparture = airportDeparture;
            this.airportArrival = airportArrival;
            this.soldTickets =soldTickets;

    }

    public FlightDTO() {

    }

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

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public Integer getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Integer soldTickets) {
        this.soldTickets = soldTickets;
    }
}
