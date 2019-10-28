package by.sum_solutions.findtrip.repository.entity;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Table(name = "airport")
public class AirportEntity extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "airport_departure", fetch = FetchType.EAGER)
    private List<FlightEntity> flight_departure;

    @OneToMany(mappedBy = "airport_arrival", fetch = FetchType.EAGER)
    private List<FlightEntity> flight_arrival;

    public AirportEntity() {
    }

    public AirportEntity(@NotNull String name, @NotNull String city, @NotNull String country, List<FlightEntity> flight_departure, List<FlightEntity> flight_arrival) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.flight_departure = flight_departure;
        this.flight_arrival = flight_arrival;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirportEntity that = (AirportEntity) o;
        return name.equals(that.name) &&
                city.equals(that.city) &&
                country.equals(that.country) &&
                flight_departure.equals(that.flight_departure) &&
                flight_arrival.equals(that.flight_arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, country, flight_departure, flight_arrival);
    }
}
