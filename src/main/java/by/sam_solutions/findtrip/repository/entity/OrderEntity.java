package by.sam_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "app_order")
public class OrderEntity extends BaseEntity {


    @NotNull
    @Column(name = "final_cost")
    private Double finalCost;

    @NotNull
    @Column(name = "order_date",columnDefinition = "timestamp")
    private Timestamp orderDate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private UserEntity user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @NotNull
    private FlightEntity flight;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;

    public OrderEntity() {
    }

    public OrderEntity(@NotNull Double finalCost, @NotNull Timestamp orderDate, @NotNull UserEntity user, @NotNull FlightEntity flight) {
        this.finalCost = finalCost;
        this.orderDate = orderDate;
        this.user = user;
        this.flight = flight;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity owner) {
        this.user = owner;
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
