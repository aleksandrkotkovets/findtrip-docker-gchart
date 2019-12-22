package by.sam_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ticket")
public class TicketEntity extends BaseEntity {

    @NotNull
    @Column(name = "seat")
    private Integer seat;

    @NotNull
    private Double finalCost;

    @NotNull
    @Column(name = "order_date",columnDefinition = "timestamp")
    private Timestamp orderDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private UserEntity owner;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @NotNull
    private FlightEntity flight;

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

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }
}
