package by.sum_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "airport")
public class AirportEntity extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "code", length = 4)
    private String code;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity;

    @OneToMany(mappedBy = "airport_departure", fetch = FetchType.EAGER)
    private List<FlightEntity> flight_departure;

    @OneToMany(mappedBy = "airport_arrival", fetch = FetchType.EAGER)
    private List<FlightEntity> flight_arrival;

    public AirportEntity() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public List<FlightEntity> getFlight_departure() {
        return flight_departure;
    }

    public void setFlight_departure(List<FlightEntity> flight_departure) {
        this.flight_departure = flight_departure;
    }

    public List<FlightEntity> getFlight_arrival() {
        return flight_arrival;
    }

    public void setFlight_arrival(List<FlightEntity> flight_arrival) {
        this.flight_arrival = flight_arrival;
    }


}
