package by.sumsolutions.findtrip.repository.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "flight")
public class FlightEntity extends BaseEntity {

    private Integer plain_id;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference(value = "cityRef1")
    @JoinColumn(name = "")
    private AirportEntity airportFrom;

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
    private Timestamp departureDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrival_date",columnDefinition = "timestamp")
    private Timestamp arrivalDate;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TicketEntity> tickets;



}
