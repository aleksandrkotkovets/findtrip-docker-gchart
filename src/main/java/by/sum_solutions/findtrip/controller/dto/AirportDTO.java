package by.sum_solutions.findtrip.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AirportDTO {

    private Long id;

    @NotNull
    @Size(min = 2,max = 4, message = "The name of the airport must have between {min} and {max} characters.")
    private  String name;

    private CityDTO cityDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }
}
