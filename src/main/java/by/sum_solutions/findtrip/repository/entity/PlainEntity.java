package by.sum_solutions.findtrip.repository.entity;

import com.sun.xml.bind.v2.util.FlattenIterator;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Table(name = "transport")
public class PlainEntity extends BaseEntity{

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "plain", fetch = FetchType.LAZY)
    private Set<FlightEntity> flights;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @NotNull
    private CompanyEntity company;

    public PlainEntity() {
    }

    public PlainEntity(@NotNull String name, Set<FlightEntity> flights, @NotNull CompanyEntity company) {
        this.name = name;
        this.flights = flights;
        this.company = company;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlainEntity that = (PlainEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(flights, that.flights) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, flights, company);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlainEntity{");
        sb.append("name='").append(name).append('\'');
        sb.append(", flights=").append(flights);
        sb.append(", company=").append(company);
        sb.append('}');
        return sb.toString();
    }
}
