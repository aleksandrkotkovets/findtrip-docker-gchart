package by.sumsolutions.findtrip.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "airport")
public class AirportEntity extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "country")
    private String country;



}
