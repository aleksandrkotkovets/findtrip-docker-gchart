package com.sam_solutions.findtrip.exception;

import com.sam_solutions.findtrip.controller.dto.CityDTO;
import com.sam_solutions.findtrip.controller.dto.FlightCriteriaDTO;

public class DatasException extends RuntimeException {

    CityDTO cityDTODepart;
    CityDTO cityDTOArriv;
    String departureDate;
    FlightCriteriaDTO flightCriteriaDTO;

    public DatasException(String msg, CityDTO cityDTODepart, CityDTO cityDTOArriv, String departureDate, FlightCriteriaDTO flightCriteriaDTO) {
        super(msg);
        this.cityDTODepart = cityDTODepart;
        this.cityDTOArriv = cityDTOArriv;
        this.departureDate = departureDate;
        this.flightCriteriaDTO = flightCriteriaDTO;
    }
}
