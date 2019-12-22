package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.CityDTO;

public class CityIncorrectException extends RuntimeException {

    CityDTO cityDepart;
    CityDTO cityArr;
    String date;

    public CityIncorrectException(String message, CityDTO cityDepart, CityDTO cityArr, String date) {
        super(message);
        this.cityDepart = cityDepart;
        this.cityArr = cityArr;
        this.date = date;
    }
}
