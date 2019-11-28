package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AirportRepository extends JpaRepository<AirportEntity, Long> {

}
