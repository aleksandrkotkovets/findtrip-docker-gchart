package by.sam_solutions.findtrip.controller.dto;

public class FlightCreateUpdateDTO {

    private Long cityFromId;
    private Long cityToId;
    private Long airportFromId;
    private Long airportToId;
    private Long dateDeparture;
    private Long dateArrival;
    private Long planeId;
    private Long companyId;
    private Integer allSeats;
    private Integer freeSeats;
    private Double ticketPrice;
    private Long id;

    public Long getCityFromId() {
        return cityFromId;
    }

    public void setCityFromId(Long cityFromId) {
        this.cityFromId = cityFromId;
    }

    public Long getCityToId() {
        return cityToId;
    }

    public void setCityToId(Long cityToId) {
        this.cityToId = cityToId;
    }

    public Long getAirportFromId() {
        return airportFromId;
    }

    public void setAirportFromId(Long airportFromId) {
        this.airportFromId = airportFromId;
    }

    public Long getAirportToId() {
        return airportToId;
    }

    public void setAirportToId(Long airportToId) {
        this.airportToId = airportToId;
    }

    public Long getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Long dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public Long getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Long dateArrival) {
        this.dateArrival = dateArrival;
    }

    public Long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getAllSeats() {
        return allSeats;
    }

    public void setAllSeats(Integer allSeats) {
        this.allSeats = allSeats;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FlightCreateUpdateDTO{" +
                "cityFromId=" + cityFromId +
                ", cityToId=" + cityToId +
                ", airportFromId=" + airportFromId +
                ", airportToId=" + airportToId +
                ", dateDeparture=" + dateDeparture +
                ", dateArrival=" + dateArrival +
                ", planeId=" + planeId +
                ", companyId=" + companyId +
                ", allSeats=" + allSeats +
                ", freeSeats=" + freeSeats +
                ", ticketPrice=" + ticketPrice +
                ", id=" + id +
                '}';
    }
}
