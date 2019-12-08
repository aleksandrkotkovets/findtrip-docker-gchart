package by.sam_solutions.findtrip.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CountryDTO {

    private  Long id;

    @NotNull
    @Size(min = 3,max = 40, message = "The name of the country must have between {min} and {max} characters.")
    private  String name;

    private  List<CityDTO> cityDTOList;

    public CountryDTO(){

    }

    public CountryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityDTO> getCityDTOList() {
        return cityDTOList;
    }

    public void setCityDTOList(List<CityDTO> cityDTOList) {
        this.cityDTOList = cityDTOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
