package by.sumsolutions.findtrip.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transport")
public class PlainEntity extends BaseEntity{

    @NotNull
    @Column(name = "name")
    private String name;

    private CompanyEntity company;


}
