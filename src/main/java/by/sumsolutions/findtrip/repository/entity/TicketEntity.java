package by.sumsolutions.findtrip.repository.entity;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Builder
@Table(name = "ticket")
public class TicketEntity extends BaseEntity {

    @NotNull
    @Column(name = "seat")
    private Integer seat;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date",columnDefinition = "timestamp")
    private Timestamp orderDate;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity owner;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private FlightEntity flight;

    public TicketEntity(@NotNull Integer seat, @NotNull Timestamp orderDate, @NotNull UserEntity owner, FlightEntity flight) {
        this.seat = seat;
        this.orderDate = orderDate;
        this.owner = owner;
        this.flight = flight;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }


}
