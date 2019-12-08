package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.CountryDTO;

public class EditCountryParametersExistException extends RuntimeException {

    CountryDTO countryDTO;

    public EditCountryParametersExistException() {
    }

    public EditCountryParametersExistException(String message) {
        super(message);
    }

    public EditCountryParametersExistException(String message, CountryDTO countryDTO) {
        super(message);
        this.countryDTO = countryDTO;
    }

    public EditCountryParametersExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EditCountryParametersExistException(Throwable cause) {
        super(cause);
    }
}
