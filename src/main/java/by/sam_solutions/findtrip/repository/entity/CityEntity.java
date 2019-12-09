package by.sam_solutions.findtrip.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class CityEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cityEntity")
    List<AirportEntity> airports;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private CountryEntity countryEntity;

    public CityEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AirportEntity> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportEntity> airports) {
        this.airports = airports;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }
}
