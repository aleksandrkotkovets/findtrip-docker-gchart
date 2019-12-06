package by.sum_solutions.findtrip.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CityDTO {

    private  Long id;

    @NotNull
    @Size(min = 3,max = 20, message = "The name of the city must have between {min} and {max} characters.")
    private  String name;

    private  List<AirportDTO> airportDTOList;

    private  CountryDTO countryDTO;

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

    public List<AirportDTO> getAirportDTOList() {
        return airportDTOList;
    }

    public void setAirportDTOList(List<AirportDTO> airportDTOList) {
        this.airportDTOList = airportDTOList;
    }

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", airportDTOList=" + airportDTOList +
                ", countryDTO=" + countryDTO +
                '}';
    }
}
