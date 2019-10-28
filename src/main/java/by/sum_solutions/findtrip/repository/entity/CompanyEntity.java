package by.sum_solutions.findtrip.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company")
public class CompanyEntity  extends BaseEntity{

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Rating rating;




}
