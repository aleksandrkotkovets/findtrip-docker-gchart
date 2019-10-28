package by.sum_solutions.findtrip.repository.entity;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Table(name = "company")
public class CompanyEntity  extends BaseEntity{

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<PlainEntity> plains;

    public CompanyEntity() {
    }

    public CompanyEntity(@NotNull String name, @NotNull Rating rating, Set<PlainEntity> plains) {
        this.name = name;
        this.rating = rating;
        this.plains = plains;
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

    public Set<PlainEntity> getPlains() {
        return plains;
    }

    public void setPlains(Set<PlainEntity> plains) {
        this.plains = plains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return name.equals(that.name) &&
                rating == that.rating &&
                plains.equals(that.plains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, plains);
    }
}
