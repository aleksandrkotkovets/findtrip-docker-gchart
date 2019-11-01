package by.sum_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ticket")
public class TicketEntity extends BaseEntity {

    @NotNull
    @Column(name = "seat")
    private Integer seat;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date",columnDefinition = "timestamp")
    private Date orderDate;


    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private UserEntity owner;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @NotNull
    private FlightEntity flight;

    public TicketEntity() {
    }

    public TicketEntity(@NotNull Integer seat, @NotNull Date orderDate, @NotNull UserEntity owner, @NotNull FlightEntity flight) {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(seat, that.seat) &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(flight, that.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seat, orderDate, owner, flight);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TicketEntity{");
        sb.append("seat=").append(seat);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", owner=").append(owner);
        sb.append(", flight=").append(flight);
        sb.append('}');
        return sb.toString();
    }
}
