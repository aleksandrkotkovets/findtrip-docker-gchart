package by.sam_solutions.findtrip.repository.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import static java.util.Objects.hash;
import static javax.persistence.CascadeType.ALL;



@Entity
@Table(name = "flight")
public class FlightEntity extends BaseEntity {

    @NotNull
    @Column(name = "free_seats")
    private Integer freeSeats;

    @NotNull
    @Column(name = "all_seats")
    private Integer allSeats;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @NotNull
    @Column(name = "departure_date", columnDefinition = "timestamp")
    private Timestamp departureDate;

    @NotNull
    @Column(name = "arrival_date", columnDefinition = "timestamp")
    private Timestamp arrivalDate;

    @OneToMany(mappedBy = "flight")
    private Set<TicketEntity> tickets;

    @ManyToOne(optional = false, cascade = ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "plane_id")
    @NotNull
    private PlaneEntity plane;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = ALL)
    @JoinColumn(name = "airport_departure_id")
    @NotNull
    private AirportEntity airportDeparture;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = ALL)
    @JoinColumn(name = "airport_arrival_id")
    @NotNull
    private AirportEntity airportArrival;

    public FlightEntity() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public @NotNull Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public @NotNull Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public PlaneEntity getPlane() {
        return plane;
    }

    public void setPlane(PlaneEntity plain) {
        this.plane = plane;
    }

    public AirportEntity getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(AirportEntity airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public AirportEntity getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(AirportEntity airportArrival) {
        this.airportArrival = airportArrival;
    }


}
