package by.sam_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company")
public class CompanyEntity  extends BaseEntity{

    @NotNull
    @Column(name = "name", length = 30)
    private String name;

    @NotNull
    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<PlaneEntity> planes;

    public CompanyEntity() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<PlaneEntity> getPlanes() {
        return planes;
    }

    public void setPlanes(Set<PlaneEntity> planes) {
        this.planes = planes;
    }

}
