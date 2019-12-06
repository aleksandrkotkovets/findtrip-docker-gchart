package by.sum_solutions.findtrip.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class CountryEntity extends BaseEntity {

    @Column(name = "name", length = 40)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE , mappedBy = "countryEntity")
    List<CityEntity> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityEntity> getCities() {
        return cities;
    }

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    public CountryEntity() {
    }

}
