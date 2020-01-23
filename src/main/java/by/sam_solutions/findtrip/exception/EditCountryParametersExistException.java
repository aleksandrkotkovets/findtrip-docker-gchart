package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.CountryDTO;

public class EditCountryParametersExistException extends RuntimeException {

    CountryDTO countryDTO;

    public EditCountryParametersExistException(String message, CountryDTO countryDTO) {
        super(message);
        this.countryDTO = countryDTO;
    }

}
