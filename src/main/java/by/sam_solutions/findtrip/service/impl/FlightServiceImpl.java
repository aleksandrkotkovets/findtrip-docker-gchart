package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.FlightCreateUpdateDTO;
import by.sam_solutions.findtrip.exception.FlightDateIncorectException;
import by.sam_solutions.findtrip.exception.FlightDateIncorrectException;
import by.sam_solutions.findtrip.repository.AirportRepository;
import by.sam_solutions.findtrip.repository.FlightRepository;
import by.sam_solutions.findtrip.repository.PlaneRepository;
import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
import by.sam_solutions.findtrip.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    AirportRepository airportRepository;

    @Override
    public void addFlight(FlightCreateUpdateDTO flightDTO) {
        Optional<PlaneEntity> planeEntity = planeRepository.findById(flightDTO.getPlaneId());

        if (!planeEntity.isPresent()) {
            throw new EntityNotFoundException("PlaneEntity with id=" + planeEntity.get().getId() + " not found");
        }

        Timestamp dateDeparture = new Timestamp(flightDTO.getDateDeparture());
        Timestamp dateArrival = new Timestamp(flightDTO.getDateArrival());



        for (FlightEntity flightEntity : planeEntity.get().getFlights()) {

            if (dateDeparture.after(flightEntity.getDepartureDate()) && dateDeparture.before(flightEntity.getArrivalDate())) {
                throw new FlightDateIncorrectException("This plane already has a flight in this time lapse!", flightDTO);
            }

            if (dateArrival.after(flightEntity.getDepartureDate()) && dateArrival.before(flightEntity.getArrivalDate())) {
                throw new FlightDateIncorrectException("This plane already has a flight in this time lapse!", flightDTO);
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
//        flightRepository.save(flightEntity);

    }

}
