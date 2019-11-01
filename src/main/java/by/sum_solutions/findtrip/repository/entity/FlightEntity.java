package by.sum_solutions.findtrip.repository.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
<<<<<<< HEAD
=======
import java.util.Objects;
>>>>>>> 156dd177bbc43cade9847023676af96a014e70d0
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

//import java.sql.Timestamp;

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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "departure_date", columnDefinition = "timestamp")
    private Date departureDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrival_date", columnDefinition = "timestamp")
    private Date arrivalDate;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<TicketEntity> tickets;

    @ManyToOne(optional = false, cascade = ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "plain_id")
    @NotNull
    private PlainEntity plain;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = ALL)
    @JoinColumn(name = "airport_departure_id")
    @NotNull
    private AirportEntity airport_departure;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = ALL)
    @JoinColumn(name = "airport_arrival_id")
    @NotNull
    private AirportEntity airport_arrival;

    public FlightEntity() {
    }

    public FlightEntity(@NotNull Integer freeSeats, @NotNull Integer allSeats, @NotNull Integer price, @NotNull Date departureDate, @NotNull Date arrivalDate, Set<TicketEntity> tickets, @NotNull PlainEntity plain, @NotNull AirportEntity airport_departure, @NotNull AirportEntity airport_arrival) {
        this.freeSeats = freeSeats;
        this.allSeats = allSeats;
        this.price = price;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.tickets = tickets;
        this.plain = plain;
        this.airport_departure = airport_departure;
        this.airport_arrival = airport_arrival;
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

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public @NotNull Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public PlainEntity getPlain() {
        return plain;
    }

    public void setPlain(PlainEntity plain) {
        this.plain = plain;
    }

    public AirportEntity getAirport_departure() {
        return airport_departure;
    }

    public void setAirport_departure(AirportEntity airport_departure) {
        this.airport_departure = airport_departure;
    }

    public AirportEntity getAirport_arrival() {
        return airport_arrival;
    }

    public void setAirport_arrival(AirportEntity airport_arrival) {
        this.airport_arrival = airport_arrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightEntity that = (FlightEntity) o;
        return freeSeats.equals(that.freeSeats) &&
                allSeats.equals(that.allSeats) &&
                price.equals(that.price) &&
                departureDate.equals(that.departureDate) &&
                arrivalDate.equals(that.arrivalDate) &&
                tickets.equals(that.tickets) &&
                plain.equals(that.plain) &&
                airport_departure.equals(that.airport_departure) &&
                airport_arrival.equals(that.airport_arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freeSeats, allSeats, price, departureDate, arrivalDate, tickets, plain, airport_departure, airport_arrival);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlightEntity{");
        sb.append("freeSeats=").append(freeSeats);
        sb.append(", allSeats=").append(allSeats);
        sb.append(", price=").append(price);
        sb.append(", departureDate=").append(departureDate);
        sb.append(", arrivalDate=").append(arrivalDate);
        sb.append(", tickets=").append(tickets);
        sb.append(", plain=").append(plain);
        sb.append(", airport_departure=").append(airport_departure);
        sb.append(", airport_arrival=").append(airport_arrival);
        sb.append('}');
        return sb.toString();
    }

}
