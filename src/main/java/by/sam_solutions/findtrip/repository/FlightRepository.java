package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;


public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    List<FlightEntity> findAllByAirportDeparture_CityEntityIdAndAirportArrival_CityEntityIdAndDepartureDateBetween(Long idCityDeparture,
                                                                                                                   Long idCityArrival,
                                                                                                                   Timestamp dateDeparture,
                                                                                                                   Timestamp finishDate,
                                                                                                                   Sort sort);
}
