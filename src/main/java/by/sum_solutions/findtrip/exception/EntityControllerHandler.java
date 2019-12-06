package by.sum_solutions.findtrip.exception;

import by.sum_solutions.findtrip.controller.dto.ApiError;
import by.sum_solutions.findtrip.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class EntityControllerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityControllerHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFound(EntityNotFoundException ex, WebRequest request) {
        LOGGER.error(ex.getMessage());
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // Country Edit parameter
    @ExceptionHandler(EditCountryParametersExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditParameterIsExist(EditCountryParametersExistException ex, WebRequest request) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("country/editCountry");
        modelAndView.addObject("country", ex.countryDTO);
        modelAndView.addObject("apiError",apiError);
        return modelAndView;
    }

    // City Edit parameter
    @ExceptionHandler(EditCityParametersExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditParameterIsExist(EditCityParametersExistException ex, WebRequest request) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/editCity");
        modelAndView.addObject("city", ex.cityDTO);
        modelAndView.addObject("countryName", ex.countryName);
        modelAndView.addObject("apiError",apiError);

        return modelAndView;
    }

}
