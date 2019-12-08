package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.CityDTO;

public class EditCityParametersExistException extends RuntimeException {

    CityDTO cityDTO;
    String countryName;

    public EditCityParametersExistException() {
    }

    public EditCityParametersExistException(String message) {
        super(message);
    }

    public EditCityParametersExistException(String message, CityDTO cityDTO,Long idCity, String countryName) {
        super(message);
        this.cityDTO = cityDTO;
        this.countryName = countryName;
    }

    public EditCityParametersExistException(String message, CityDTO cityDTO, String countryName) {
        super(message);
        this.cityDTO = cityDTO;
        this.countryName = countryName;
    }

    public EditCityParametersExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EditCityParametersExistException(Throwable cause) {
        super(cause);
    }
}
