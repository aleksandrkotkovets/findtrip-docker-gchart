package by.sam_solutions.findtrip.repository.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "country")
public class CountryEntity extends BaseEntity {

    @Column(name = "name", length = 40)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE , mappedBy = "countryEntity")
    Set<CityEntity> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CityEntity> getCities() {
        return cities;
    }

    public void setCities(Set<CityEntity> cities) {
        this.cities = cities;
    }

    public CountryEntity() {
    }

}
