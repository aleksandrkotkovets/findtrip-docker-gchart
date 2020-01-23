package by.sam_solutions.findtrip.service.impl;


import static org.junit.jupiter.api.Assertions.*;

import by.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;
import by.sam_solutions.findtrip.exception.FlightDateIncorrectException;
import by.sam_solutions.findtrip.repository.*;
import by.sam_solutions.findtrip.repository.entity.*;
import by.sam_solutions.findtrip.service.CityService;
import by.sam_solutions.findtrip.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private PlaneRepository planeRepository;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private CityService cityService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Test(expected = EntityNotFoundException.class)
    public void addFlightPlaneNotFoundException() {
        flightService.addFlight(new FlightCreateUpdateDTO());
        Optional<PlaneEntity> planeEntityEmpty = Optional.empty();
        when(planeRepository.findById(1L)).thenReturn(planeEntityEmpty);
    }

    @Test(expected = FlightDateIncorrectException.class)
    public void addFlight_DateMoreThreeDays() {
        FlightCreateUpdateDTO flightCreateUpdateDTO = new FlightCreateUpdateDTO();
        flightCreateUpdateDTO.setPlaneId(1L);
        Optional<PlaneEntity> planeEntity = Optional.of(new PlaneEntity("Boing", "B232"));
        when(planeRepository.findById(flightCreateUpdateDTO.getPlaneId())).thenReturn(planeEntity);
        flightCreateUpdateDTO.setDateDeparture(1579726264000L); //2020-12-22 20-51
        flightCreateUpdateDTO.setDateArrival(1580071864000L); //2020-12-26 20-51
        flightService.addFlight(flightCreateUpdateDTO);
    }

    @Test(expected = FlightDateIncorrectException.class)
    public void addFlightFlightDate_DateDepartBetweenFlightsDate() {
        //first exception
        FlightCreateUpdateDTO flightCreateUpdateDTO = new FlightCreateUpdateDTO();
        flightCreateUpdateDTO.setPlaneId(1L);
        Optional<PlaneEntity> planeEntity = Optional.of(new PlaneEntity("Boing", "B232"));

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(2L);
        flightEntity.setDepartureDate(new Timestamp(1579726264000L));  //2020-01-22 23-51
        flightEntity.setArrivalDate(new Timestamp(1579903204000L));  //2020-01-25 01-00
        planeEntity.get().setFlights(Set.of(flightEntity));

        when(planeRepository.findById(flightCreateUpdateDTO.getPlaneId())).thenReturn(planeEntity);
        flightCreateUpdateDTO.setDateDeparture(1579812664000L); // 2020-01-23 23-51
        flightCreateUpdateDTO.setDateArrival(1579899064000L); //2020-01-24 23-51
        flightService.addFlight(flightCreateUpdateDTO);
    }

    @Test(expected = FlightDateIncorrectException.class)
    public void addFlightFlightDate_DateArrivalBetweenFlightsDate() {
        //second exception
        FlightCreateUpdateDTO flightCreateUpdateDTO = new FlightCreateUpdateDTO();
        flightCreateUpdateDTO.setPlaneId(1L);
        Optional<PlaneEntity> planeEntity = Optional.of(new PlaneEntity("Boing", "B232"));

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setId(2L);
        flightEntity.setDepartureDate(new Timestamp(1579726264000L));  //2020-01-22 23-51
        flightEntity.setArrivalDate(new Timestamp(1579903204000L));  //2020-01-25 01-00
        planeEntity.get().setFlights(Set.of(flightEntity));

        when(planeRepository.findById(flightCreateUpdateDTO.getPlaneId())).thenReturn(planeEntity);
        flightCreateUpdateDTO.setDateDeparture(1579626004000L); // 2020-01-21 17-00
        flightCreateUpdateDTO.setDateArrival(1579795204000L); //2020-01-23 19-00
        flightService.addFlight(flightCreateUpdateDTO);
    }

    @Test()
    public void addFlightFlight() {
        FlightCreateUpdateDTO flightCreateUpdateDTO = new FlightCreateUpdateDTO();
        flightCreateUpdateDTO.setPlaneId(1L);

        Optional<PlaneEntity> planeEntity = Optional.of(new PlaneEntity("Boing", "B232"));
        planeEntity.get().setFlights(Set.of());
        planeEntity.get().setId(1L);

        when(planeRepository.findById(flightCreateUpdateDTO.getPlaneId())).thenReturn(planeEntity);
        flightCreateUpdateDTO.setDateDeparture(1579626004000L); // 2020-01-21 17-00
        flightCreateUpdateDTO.setDateArrival(1579795204000L); //2020-01-23 19-00

        Optional<AirportEntity> airportEntityFrom = Optional.of(new AirportEntity());
        airportEntityFrom.get().setId(2L);
        Optional<AirportEntity> airportEntityTo = Optional.of(new AirportEntity());
        airportEntityTo.get().setId(3L);
        flightCreateUpdateDTO.setAirportFromId(2L);
        flightCreateUpdateDTO.setAirportToId(3L);

        when(airportRepository.findById(airportEntityFrom.get().getId())).thenReturn(airportEntityFrom);
        when(airportRepository.findById(airportEntityTo.get().getId())).thenReturn(airportEntityTo);

        flightService.addFlight(flightCreateUpdateDTO);
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setPlane(planeEntity.get());
        flightEntity.setDepartureDate(new Timestamp(1579626004000L));
        flightEntity.setArrivalDate(new Timestamp(1579795204000L));
        flightEntity.setAirportDeparture(airportEntityFrom.get());
        flightEntity.setAirportArrival(airportEntityTo.get());
        flightEntity.setStatus(FlightStatus.ACTIVE);

        verify(flightRepository).save(refEq(flightEntity));


    }
    @Test
    public void getById() {
    }

    @Test
    public void getNumberSoldTicketById() {
    }

    @Test
    public void canceledFlight() {
    }

    @Test
    public void findFlightsByCriteria() {
    }
}