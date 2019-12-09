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

    @NotNull
    @Column(name = "side_number", length = 10)
    private String sideNumber;


    @OneToMany(mappedBy = "plane")
    private Set<FlightEntity> flights;

    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @NotNull
    private CompanyEntity company;

    public PlaneEntity() {
    }

    public PlaneEntity(String name, String sideNumber, CompanyEntity companyEntity) {
        this.name = name;
        this.sideNumber = sideNumber;
        this.company = companyEntity;
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


    public String getSideNumber() {
        return sideNumber;
    }

    public void setSideNumber(String sideNumber) {
        this.sideNumber = sideNumber;
    }
}
