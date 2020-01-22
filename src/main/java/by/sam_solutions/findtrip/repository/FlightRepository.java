package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.FlightEntity;
import by.sam_solutions.findtrip.repository.entity.FlightStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Long>, JpaSpecificationExecutor<FlightEntity> {

    List<FlightEntity> findAllByStatus(FlightStatus status, Sort departureDate);
}
