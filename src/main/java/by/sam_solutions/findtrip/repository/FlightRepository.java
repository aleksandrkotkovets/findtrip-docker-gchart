package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

}
