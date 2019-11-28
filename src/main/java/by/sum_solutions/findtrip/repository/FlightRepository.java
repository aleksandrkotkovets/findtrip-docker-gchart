package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

}
