package by.sam_solutions.findtrip.repository.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company")
public class CompanyEntity extends BaseEntity {

    @NotNull
    @Column(name = "name", length = 30)
    private String name;

    @NotNull
    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @OneToMany(mappedBy = "company")
    private Set<PlaneEntity> planes;

    public CompanyEntity() {
    }

    public CompanyEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public CompanyEntity(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
    }

    public CompanyEntity(Long id, String name, Rating rating, Set<PlaneEntity> planes) {
        super(id);
        this.name = name;
        this.rating = rating;
        this.planes = planes;
    }

    public CompanyEntity(Long id, String name, Rating rating) {
        super(id);
        this.name = name;
        this.rating = rating;
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
