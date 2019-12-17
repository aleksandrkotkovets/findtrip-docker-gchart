package by.sam_solutions.findtrip.exception;

import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.controller.dto.CompanyDTO;
import by.sam_solutions.findtrip.repository.*;
import by.sam_solutions.findtrip.repository.entity.AirportEntity;
import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
import by.sam_solutions.findtrip.repository.entity.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@ControllerAdvice
public class EntityControllerHandler {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    CompanyRepository companyRepository;


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
        modelAndView.addObject("apiError",apiError);
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
        modelAndView.addObject("apiError",apiError);

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
        modelAndView.addObject("apiError",apiError);

        return modelAndView;
    }


    // Airport add parameter
    @ExceptionHandler(AirportAddParameterExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditParameterIsExist(AirportAddParameterExistException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("airport/addAirport");
        modelAndView.addObject("name", ex.airportDTO.getName());
        modelAndView.addObject("code", ex.airportDTO.getCode());
        modelAndView.addObject("countries", countryRepository.findAll());
        modelAndView.addObject("apiError",apiError);

        return modelAndView;
    }

    // Company edit parameter
    @ExceptionHandler(EditCompanyParameterExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditParameterIsExist(EditCompanyParameterExistException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("company/editCompany");
        modelAndView.addObject("company", ex.companyDTO);
        modelAndView.addObject("ratingTypes", Rating.values());
        modelAndView.addObject("apiError",apiError);

        return modelAndView;
    }

    // Plane Edit parameter
    @ExceptionHandler(EditPlaneParametersExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleEditPlaneParameterIsExist(EditPlaneParametersExistException ex, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("plane/editPlane");
        modelAndView.addObject("plane", ex.planeDTO);
        modelAndView.addObject("companyName", ex.companyName);
        modelAndView.addObject("companyId", ex.companyId);
        modelAndView.addObject("apiError",apiError);

        return modelAndView;

    }

   /* // Company edit parameter
    @ExceptionHandler(FlightDateIncorrectException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView handleDateParameter(FlightDateIncorrectException ex) {
        String error = ex.getMessage();

     *//*   ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        ModelAndView modelAndView = new ModelAndView();
        AirportEntity airportEntityDep = airportRepository.findById(ex.flightDTO.getAirportFromId()).get();
        AirportEntity airportEntityArr = airportRepository.findById(ex.flightDTO.getAirportToId()).get();
        modelAndView.setViewName("flight/addFlight");
        modelAndView.addObject("idCountry_fr",airportEntityDep.getCityEntity().getCountryEntity());
        modelAndView.addObject("idCountry_to",airportEntityArr.getCityEntity().getCountryEntity());
        modelAndView.addObject("idCity_fr",airportEntityDep.getCityEntity());
        modelAndView.addObject("idCity_to",airportEntityArr.getCityEntity());
        modelAndView.addObject("idAirport_fr",airportEntityDep);
        modelAndView.addObject("idAirport_to",airportEntityArr);
        modelAndView.addObject("picker1",new Date(ex.flightDTO.getDateDeparture()));
        modelAndView.addObject("picker2",new Date(ex.flightDTO.getDateArrival()));
        modelAndView.addObject("idCompany", companyRepository.findById(ex.flightDTO.getCompanyId()));
        modelAndView.addObject("idPlane", planeRepository.findById(ex.flightDTO.getPlaneId()));
        modelAndView.addObject("allSeats", ex.flightDTO.getAllSeats());
        modelAndView.addObject("freeSeats", ex.flightDTO.getFreeSeats());
        modelAndView.addObject("freeSeats", ex.flightDTO.getFreeSeats());
        modelAndView.addObject("price", ex.flightDTO.getTicketPrice());
        modelAndView.addObject("apiError",apiError);*//*

        return modelAndView;
    }
*/


    @ExceptionHandler({FlightDateIncorrectException.class})
    public ResponseEntity<Object> handleEmailExist(FlightDateIncorrectException ex, WebRequest request) {
        String error = ex.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }



}
