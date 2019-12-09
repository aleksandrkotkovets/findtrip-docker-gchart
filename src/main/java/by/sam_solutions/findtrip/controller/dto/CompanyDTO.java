package by.sam_solutions.findtrip.controller.dto;

import by.sam_solutions.findtrip.repository.entity.Rating;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CompanyDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    private Rating rating;

    private List<PlaneDTO> planeDTOList;

    public CompanyDTO(){}

    public CompanyDTO(Long id, String name, Rating rating) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PlaneDTO> getPlaneDTOList() {
        return planeDTOList;
    }

    public void setPlaneDTOList(List<PlaneDTO> planeDTOList) {
        this.planeDTOList = planeDTOList;
    }
}
