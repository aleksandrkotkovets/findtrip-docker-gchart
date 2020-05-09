package com.sam_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "airport")
public class AirportEntity extends BaseEntity {

    @NotNull
    @Column(name = "name", length = 30)
    private String name;

    @NotNull
    @Column(name = "code", length = 5)
    private String code;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity;

    @OneToMany(mappedBy = "airportDeparture", fetch = FetchType.EAGER)
    private List<FlightEntity> flightDepartures;

    @OneToMany(mappedBy = "airportArrival", fetch = FetchType.EAGER)
    private List<FlightEntity> flightArrivals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public List<FlightEntity> getFlightDepartures() {
        return flightDepartures;
    }

    public void setFlightDepartures(List<FlightEntity> flightDepartures) {
        this.flightDepartures = flightDepartures;
    }

    public List<FlightEntity> getFlightArrivals() {
        return flightArrivals;
    }

    public void setFlightArrivals(List<FlightEntity> flightArrivals) {
        this.flightArrivals = flightArrivals;
    }

    @Override
    public String toString() {
        return "AirportEntity{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", cityEntity=" + cityEntity +
                ", flightDepartures=" + flightDepartures +
                ", flightArrivals=" + flightArrivals +
                '}';
    }
}
