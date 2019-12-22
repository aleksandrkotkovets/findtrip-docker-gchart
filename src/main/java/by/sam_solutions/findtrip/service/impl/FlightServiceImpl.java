package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.*;
import by.sam_solutions.findtrip.exception.CityIncorrectException;
import by.sam_solutions.findtrip.exception.FlightDateIncorrectException;
import by.sam_solutions.findtrip.repository.AirportRepository;
import by.sam_solutions.findtrip.repository.FlightRepository;
import by.sam_solutions.findtrip.repository.PlaneRepository;
import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
import by.sam_solutions.findtrip.service.CityService;
import by.sam_solutions.findtrip.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {


    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    CityService cityService;

    private final int GET_HOURS_FROM_MILLISECONDS = 3_600_000;
    private final int THREE_DAYS = 72;
    private final int DAY_IN_MILLISECONDS = 86_399_000;

    @Override

    public void addFlight(FlightCreateUpdateDTO flightDTO) {
        Optional<PlaneEntity> planeEntity = planeRepository.findById(flightDTO.getPlaneId());

        if (!planeEntity.isPresent()) {
            throw new EntityNotFoundException("PlaneEntity with id=" + planeEntity.get().getId() + " not found");
        }

        Timestamp dateDeparture = new Timestamp(flightDTO.getDateDeparture());
        Timestamp dateArrival = new Timestamp(flightDTO.getDateArrival());

        if ((dateArrival.getTime() - dateDeparture.getTime()) / GET_HOURS_FROM_MILLISECONDS > THREE_DAYS) {
            throw new FlightDateIncorrectException("Flight duration more than three days", flightDTO);
        }

        for (FlightEntity flightEntity : planeEntity.get().getFlights()) {

            if (dateDeparture.after(flightEntity.getDepartureDate()) && dateDeparture.before(flightEntity.getArrivalDate())) {
                throw new FlightDateIncorrectException("This plane already has a flight in this time lapse", flightDTO);
            }

            if (dateArrival.after(flightEntity.getDepartureDate()) && dateArrival.before(flightEntity.getArrivalDate())) {
                throw new FlightDateIncorrectException("This plane already has a flight in this time lapse", flightDTO);
            }

        }

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setAllSeats(flightDTO.getAllSeats());
        flightEntity.setArrivalDate(dateArrival);
        flightEntity.setDepartureDate(dateDeparture);
        flightEntity.setFreeSeats(flightDTO.getFreeSeats());
        flightEntity.setPrice(flightDTO.getTicketPrice());
        flightEntity.setAirportArrival(airportRepository.findById(flightDTO.getAirportToId()).get());
        flightEntity.setAirportDeparture(airportRepository.findById(flightDTO.getAirportFromId()).get());
        flightEntity.setPlane(planeEntity.get());
        flightRepository.save(flightEntity);
    }

    @Override
    public FlightDTO getById(Long id) {
        Optional<FlightEntity> flightEntity = flightRepository.findById(id);

        if (!flightEntity.isPresent()) {
            throw new EntityNotFoundException("Flight with id=" + id + " not found");
        }

        return mapFlightDTO(flightEntity.get());
    }

    @Override
    public void edit(FlightCreateUpdateDTO flightDTO) {
        FlightEntity editEntity = flightRepository.findById(flightDTO.getId()).get();

        if (editEntity == null) {
            throw new EntityNotFoundException("Flight with id=" + flightDTO.getId() + " not found");
        }

        Timestamp dateDeparture = new Timestamp(flightDTO.getDateDeparture());
        Timestamp dateArrival = new Timestamp(flightDTO.getDateArrival());

        PlaneEntity editPlane = planeRepository.findById(flightDTO.getPlaneId()).get();

        if (flightDTO.getPlaneId() != editEntity.getPlane().getId()) {

            for (FlightEntity flightEntity : editPlane.getFlights()) {

                if (dateDeparture.after(flightEntity.getDepartureDate()) &&
                        dateDeparture.before(flightEntity.getArrivalDate())) {

                    throw new FlightDateIncorrectException("This plane already has a flight in this time lapse", flightDTO);
                }

                if (dateArrival.after(flightEntity.getDepartureDate()) &&
                        dateArrival.before(flightEntity.getArrivalDate())) {

                    throw new FlightDateIncorrectException("This plane already has a flight in this time lapse", flightDTO);
                }
            }

            editEntity.setPlane(editEntity.getPlane());

        } else if (!editEntity.getArrivalDate().equals(dateArrival) &&
                !editEntity.getDepartureDate().equals(dateDeparture)) {

            for (FlightEntity flightEntity : editPlane.getFlights()) {

                if (flightDTO.getId() == flightEntity.getId())
                    continue;

                if (dateDeparture.after(flightEntity.getDepartureDate()) &&
                        dateDeparture.before(flightEntity.getArrivalDate())) {
                    throw new FlightDateIncorrectException("This plane already has a flight in this time lapse", flightDTO);
                }

                if (dateArrival.after(flightEntity.getDepartureDate()) &&
                        dateArrival.before(flightEntity.getArrivalDate())) {
                    throw new FlightDateIncorrectException("This plane already has a flight in this time lapse", flightDTO);
                }
            }

            editEntity.setArrivalDate(dateArrival);
            editEntity.setDepartureDate(dateDeparture);

        }


        if (flightDTO.getAirportToId() != editEntity.getAirportArrival().getId()) {
            editEntity.setAirportArrival(airportRepository.findById(flightDTO.getAirportToId()).get());
        }

        if (flightDTO.getAirportFromId() != editEntity.getAirportDeparture().getId()) {
            editEntity.setAirportDeparture(airportRepository.findById(flightDTO.getAirportFromId()).get());
        }

        editEntity.setAllSeats(flightDTO.getAllSeats());
        editEntity.setFreeSeats(flightDTO.getFreeSeats());
        editEntity.setPrice(flightDTO.getTicketPrice());

        flightRepository.save(editEntity);

    }

    @Override
    public List<FlightDTO> findAll() {
        List<FlightEntity> flightEntityList = flightRepository.findAll(Sort.by("departureDate").ascending());

        return mapListFlightDTO(flightEntityList);
    }

    @Override
    public List<FlightDTO> findFlightsByCriteria(Long idCityDeparture, Long idCityArrival, String dateDeparture) {

        if (idCityDeparture == idCityArrival) {
            throw new CityIncorrectException("Enter_different_cities", cityService.findOne(idCityDeparture), cityService.findOne(idCityArrival), dateDeparture);
        }

        List<FlightEntity> flightEntities = new ArrayList<>();
        try {


            Timestamp dateD = parseDate(dateDeparture);
            Timestamp currD = parseDate(parseDate(new Timestamp(new Date().getTime())));
            Timestamp finishD = new Timestamp(dateD.getTime() + DAY_IN_MILLISECONDS);


            if (dateD.before(currD)) {
                throw new CityIncorrectException("Incorrect_dates", cityService.findOne(idCityDeparture), cityService.findOne(idCityArrival), dateDeparture);
            }

            if (dateD.equals(currD)) {
                dateD = new Timestamp(new Date().getTime());
            }

            flightEntities = flightRepository.findAllByAirportDeparture_CityEntityIdAndAirportArrival_CityEntityIdAndDepartureDateBetween(idCityDeparture, idCityArrival, dateD, finishD, Sort.by("departureDate"));


        } catch (ParseException e) {
            e.printStackTrace();
        }


        return mapListFlightDTO(flightEntities);
    }

    private String parseTime(Timestamp timestamp) {
        String parseTime;
        int hours = timestamp.getHours();
        int minutes = timestamp.getMinutes();

        if (hours < 10) {
            parseTime = "0" + hours + ":";
        } else {
            parseTime = hours + ":";
        }

        if (minutes < 10) {
            parseTime += "0" + minutes;
        } else {
            parseTime += minutes + "";
        }
        return parseTime;
    }

    private String parseDate(Timestamp timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd ").format(timestamp.getTime());

    }

    private Timestamp parseDate(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateD = formatter.parse(date);
        Timestamp timestampD = new Timestamp(dateD.getTime());
        return timestampD;
    }

    private String calcTravelTime(Timestamp timeD, Timestamp timeA) {

        long milliseconds = (timeA.getTime() - timeD.getTime());
        int seconds = (int) milliseconds / 1000;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        String travelTime = hours + "h " + minutes + " m";
        return travelTime;
    }

    private FlightDTO mapFlightDTO(FlightEntity flightEntity) {
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.setId(flightEntity.getId());
        flightDTO.setAllSeats(flightEntity.getAllSeats());
        flightDTO.setFreeSeats(flightEntity.getFreeSeats());
        flightDTO.setPrice(flightEntity.getPrice());
        flightDTO.setDepartureDate(flightEntity.getDepartureDate());
        flightDTO.setArrivalDate(flightEntity.getArrivalDate());

        CountryDTO countryDepartureDTO = new CountryDTO();
        countryDepartureDTO.setId(flightEntity.getAirportDeparture().getCityEntity().getCountryEntity().getId());
        countryDepartureDTO.setName(flightEntity.getAirportDeparture().getCityEntity().getCountryEntity().getName());

        CityDTO cityDepartureDTO = new CityDTO();
        cityDepartureDTO.setId(flightEntity.getAirportDeparture().getCityEntity().getId());
        cityDepartureDTO.setName(flightEntity.getAirportDeparture().getCityEntity().getName());
        cityDepartureDTO.setCountryDTO(countryDepartureDTO);

        AirportDTO airportDepartureDTO = new AirportDTO();
        airportDepartureDTO.setId(flightEntity.getAirportDeparture().getId());
        airportDepartureDTO.setCode(flightEntity.getAirportDeparture().getCode());
        airportDepartureDTO.setName(flightEntity.getAirportDeparture().getName());
        airportDepartureDTO.setCityDTO(cityDepartureDTO);

        flightDTO.setAirportDeparture(airportDepartureDTO);

        CountryDTO countryArrivalDTO = new CountryDTO();
        countryArrivalDTO.setId(flightEntity.getAirportArrival().getCityEntity().getCountryEntity().getId());
        countryArrivalDTO.setName(flightEntity.getAirportArrival().getCityEntity().getCountryEntity().getName());

        CityDTO cityArrivalDTO = new CityDTO();
        cityArrivalDTO.setId(flightEntity.getAirportArrival().getCityEntity().getId());
        cityArrivalDTO.setName(flightEntity.getAirportArrival().getCityEntity().getName());
        cityArrivalDTO.setCountryDTO(countryArrivalDTO);

        AirportDTO airportArrivalDTO = new AirportDTO();
        airportArrivalDTO.setId(flightEntity.getAirportArrival().getId());
        airportArrivalDTO.setCode(flightEntity.getAirportArrival().getCode());
        airportArrivalDTO.setName(flightEntity.getAirportArrival().getName());
        airportArrivalDTO.setCityDTO(cityArrivalDTO);

        flightDTO.setAirportArrival(airportArrivalDTO);

        PlaneDTO planeDTO = new PlaneDTO();
        planeDTO.setId(flightEntity.getPlane().getId());
        planeDTO.setSideNumber(flightEntity.getPlane().getSideNumber());
        planeDTO.setName(flightEntity.getPlane().getName());

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(flightEntity.getPlane().getCompany().getId());
        companyDTO.setName(flightEntity.getPlane().getCompany().getName());
        companyDTO.setRating(flightEntity.getPlane().getCompany().getRating());
        planeDTO.setCompanyDTO(companyDTO);

        flightDTO.setPlane(planeDTO);


        String travelTime = calcTravelTime(flightDTO.getDepartureDate(), flightDTO.getArrivalDate());
        flightDTO.setTravelTime(travelTime);

        String timeD = parseTime(flightDTO.getDepartureDate());
        flightDTO.setTimeDeparture(timeD);

        String timeA = parseTime(flightDTO.getArrivalDate());
        flightDTO.setTimeArrival(timeA);

        flightDTO.setDateDeparture(parseDate(flightDTO.getDepartureDate()));
        flightDTO.setDateArrival(parseDate(flightDTO.getArrivalDate()));

        return flightDTO;
    }

    private List<FlightDTO> mapListFlightDTO(List<FlightEntity> flightEntityList) {
        List<FlightDTO> flightDTOList = flightEntityList.stream()
                .map(a -> new FlightDTO(
                        a.getId(),
                        a.getFreeSeats(),
                        a.getAllSeats(),
                        a.getPrice(),
                        a.getDepartureDate(),
                        a.getArrivalDate(),
                        new PlaneDTO(a.getPlane().getId(), a.getPlane().getName(), a.getPlane().getSideNumber(),
                                new CompanyDTO(a.getPlane().getCompany().getId(), a.getPlane().getCompany().getName(), a.getPlane().getCompany().getRating())),
                        new AirportDTO(a.getAirportDeparture().getId(), a.getAirportDeparture().getName(), a.getAirportDeparture().getCode(),
                                new CityDTO(a.getAirportDeparture().getCityEntity().getId(), a.getAirportDeparture().getCityEntity().getName(),
                                        new CountryDTO(a.getAirportDeparture().getCityEntity().getCountryEntity().getId(), a.getAirportDeparture().getCityEntity().getCountryEntity().getName()))), /*airportDeparture*/
                        new AirportDTO(a.getAirportArrival().getId(), a.getAirportArrival().getName(), a.getAirportArrival().getCode(),
                                new CityDTO(a.getAirportArrival().getCityEntity().getId(), a.getAirportArrival().getCityEntity().getName(),
                                        new CountryDTO(a.getAirportArrival().getCityEntity().getCountryEntity().getId(), a.getAirportArrival().getCityEntity().getCountryEntity().getName()))) /*airportArrival*/
                ))
                .collect(Collectors.toList());

        String travelTime;
        String timeD;
        String timeA;

        for (FlightDTO flightDTO : flightDTOList) {

            travelTime = calcTravelTime(flightDTO.getDepartureDate(), flightDTO.getArrivalDate());
            flightDTO.setTravelTime(travelTime);

            timeD = parseTime(flightDTO.getDepartureDate());
            flightDTO.setTimeDeparture(timeD);

            timeA = parseTime(flightDTO.getArrivalDate());
            flightDTO.setTimeArrival(timeA);

            flightDTO.setDateDeparture(parseDate(flightDTO.getDepartureDate()));
            flightDTO.setDateArrival(parseDate(flightDTO.getArrivalDate()));

        }
        return flightDTOList;
    }
}
