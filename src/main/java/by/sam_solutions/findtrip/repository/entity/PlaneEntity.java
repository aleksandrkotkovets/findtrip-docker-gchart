package by.sam_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "transport")
public class PlaneEntity extends BaseEntity{

    @NotNull
    @Column(name = "name", length = 30)
    private String name;

    @OneToMany(mappedBy = "plane", fetch = FetchType.LAZY)
    private Set<FlightEntity> flights;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @NotNull
    private CompanyEntity company;

    public PlaneEntity() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<FlightEntity> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightEntity> flights) {
        this.flights = flights;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }


}
