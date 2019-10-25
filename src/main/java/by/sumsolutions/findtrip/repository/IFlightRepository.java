package by.sumsolutions.findtrip.repository;

import by.sumsolutions.findtrip.repository.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightRepository extends JpaRepository<FlightEntity, Long> {

}
