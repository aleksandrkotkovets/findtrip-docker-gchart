package com.sam_solutions.findtrip.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CityDTO {

    private  Long id;

    @NotNull
    @Size(min = 3,max = 20)
    private  String name;

    private  List<AirportDTO> airportDTOList;

    private  CountryDTO countryDTO;

    public CityDTO(){

    }

    public CityDTO(Long id, String nameCity, Long idCountry, String nameCountry) {
        this.id = id;
        this.name = nameCity;
        this.countryDTO = new CountryDTO(idCountry, nameCountry);
    }

    public CityDTO(Long idCity) {
        this.id = idCity;
    }

    public CityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityDTO(Long id, String name, CountryDTO countryDTO) {
        this.id = id;
        this.name = name;
        this.countryDTO = countryDTO;
    }

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
