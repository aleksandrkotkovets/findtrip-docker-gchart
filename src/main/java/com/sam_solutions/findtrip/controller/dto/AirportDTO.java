package com.sam_solutions.findtrip.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AirportDTO {

    private Long id;

    @NotNull
    @Size(min = 2,max = 4)
    private  String name;

    @NotNull
    @Size(min = 2 , max = 5)
    private String code;


    private CityDTO cityDTO;

    public AirportDTO(){

    }
    public AirportDTO(String name, String code, Long idCity) {
        this.name = name;
        this.code = code;
        this.cityDTO = new CityDTO(idCity);
    }

    public AirportDTO(Long id, String name, String code) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public AirportDTO(Long id, String name, String code, CityDTO cityDTO) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.cityDTO = cityDTO;
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

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
