package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.repository.*;
import by.sam_solutions.findtrip.repository.entity.Rating;
import by.sam_solutions.findtrip.service.CountryService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class EntityControllerHandler {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FlightRepository flightRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(EntityControllerHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFound(EntityNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // Country Edit parameter
    @ExceptionHandler(EditCountryParametersExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditParameterIsExist(EditCountryParametersExistException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("country/editCountry");
        modelAndView.addObject("country", ex.countryDTO);
        modelAndView.addObject("apiError", apiError);
        return modelAndView;
    }

    // City Edit parameter
    @ExceptionHandler(EditCityParametersExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditParameterIsExist(EditCityParametersExistException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/editCity");
        modelAndView.addObject("city", ex.cityDTO);
        modelAndView.addObject("countryName", ex.countryName);
        modelAndView.addObject("apiError", apiError);

        return modelAndView;
    }

    // Airport Edit parameter
    @ExceptionHandler(EditAirportParametersExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditParameterIsExist(EditAirportParametersExistException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("airport/editAirport");
        modelAndView.addObject("airport", ex.airportDTO);
        modelAndView.addObject("apiError", apiError);

        return modelAndView;
    }


    // Airport add parameter
    @ExceptionHandler(AirportAddParameterExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleAirportAddParameterExist(AirportAddParameterExistException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("airport/addAirport");
        modelAndView.addObject("name", ex.airportDTO.getName());
        modelAndView.addObject("code", ex.airportDTO.getCode());
        modelAndView.addObject("countries", countryRepository.findAll());
        modelAndView.addObject("apiError", apiError);

        return modelAndView;
    }

    // Company edit parameter
    @ExceptionHandler(EditCompanyParameterExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditCompanyParameterExist(EditCompanyParameterExistException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("company/editCompany");
        modelAndView.addObject("company", ex.companyDTO);
        modelAndView.addObject("ratingTypes", Rating.values());
        modelAndView.addObject("apiError", apiError);

        return modelAndView;
    }

    // Plane Edit parameter
    @ExceptionHandler(EditPlaneParametersExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditPlaneParameterIsExist(EditPlaneParametersExistException ex, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("plane/editPlane");
        modelAndView.addObject("plane", ex.planeDTO);
        modelAndView.addObject("companyName", ex.companyName);
        modelAndView.addObject("companyId", ex.companyId);
        modelAndView.addObject("apiError", apiError);

        return modelAndView;

    }

    @ExceptionHandler({FlightDateIncorrectException.class})
    public ResponseEntity<Object> handleFlightDateIncorrect(FlightDateIncorrectException ex, WebRequest request) {
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    // Country Edit parameter
    @ExceptionHandler(CityIncorrectException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleIncorrectCities(CityIncorrectException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("flight/showFlights");
        modelAndView.addObject("picker1", ex.date);
        modelAndView.addObject("city_from", ex.cityDepart);
        modelAndView.addObject("city_to", ex.cityArr);
        modelAndView.addObject("countries", countryService.findAll(Sort.by("name")));
        modelAndView.addObject("flights", null);
        modelAndView.addObject("flightCriteriaDTO", ex.flightCriteriaDTO);
        modelAndView.addObject("apiError", apiError);
        return modelAndView;
    }


    @ExceptionHandler(DatasException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleFillDatas(DatasException ex) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("flight/showFlights");
        modelAndView.addObject("picker1", ex.departureDate);
        modelAndView.addObject("city_from", ex.cityDTODepart);
        modelAndView.addObject("city_to", ex.cityDTOArriv);
        modelAndView.addObject("countries", countryService.findAll(Sort.by("name")));
        modelAndView.addObject("flights", null);
        modelAndView.addObject("flightCriteriaDTO", ex.flightCriteriaDTO);
        modelAndView.addObject("apiError", apiError);
        return modelAndView;
    }

    //Order on this flight exist
    // Some code
    @ExceptionHandler(OrderSeatsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleIncorrectOrderSeats(OrderSeatsException ex) {
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler(OrderOnThisFlightAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleOrderOnThisFlightAlreadyExist(OrderOnThisFlightAlreadyExistException ex) {
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(WalletBalanceException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleIncorrectBalance(WalletBalanceException ex) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wallet/replenishBalance");
        modelAndView.addObject("bankCard", ex.getBankCardDTO());
        modelAndView.addObject("apiError", apiError);
        return modelAndView;
    }

    @ExceptionHandler(WalletIncorrectBalanceException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleIncorrectWalletBalance(WalletIncorrectBalanceException ex) {
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({FlightStatusIncorrectException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleFlightStatusIncorrect(FlightStatusIncorrectException ex) {
        return getModelAndView(ex);
    }

    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handlePayments(PaymentException ex) {
        return getModelAndView(ex);
    }

    @NotNull
    private ModelAndView getModelAndView(RuntimeException ex) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("statuscode/409");
        modelAndView.addObject("apiError", apiError);
        return modelAndView;
    }


}
