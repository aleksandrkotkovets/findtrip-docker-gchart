package com.sam_solutions.findtrip.exception;

import com.sam_solutions.findtrip.controller.dto.CityDTO;
import com.sam_solutions.findtrip.controller.dto.FlightCriteriaDTO;

public class CityIncorrectException extends RuntimeException {

    CityDTO cityDepart;
    CityDTO cityArr;
    String date;
    FlightCriteriaDTO flightCriteriaDTO;

    public CityIncorrectException(String message, CityDTO cityDepart, CityDTO cityArr, String date, FlightCriteriaDTO flightCriteriaDTO) {
        super(message);
        this.cityDepart = cityDepart;
        this.cityArr = cityArr;
        this.date = date;
        this.flightCriteriaDTO = flightCriteriaDTO;
    }
}
