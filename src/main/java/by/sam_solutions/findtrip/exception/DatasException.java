package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.CityDTO;

public class DatasException extends RuntimeException {

    CityDTO cityDTODepart;
    CityDTO cityDTOArriv;
    String departureDate;
    public DatasException(String msg, CityDTO cityDTODepart, CityDTO cityDTOArriv, String departureDate) {
        super(msg);
        this.cityDTODepart = cityDTODepart;
        this.cityDTOArriv = cityDTOArriv;
        this.departureDate = departureDate;
    }
}
