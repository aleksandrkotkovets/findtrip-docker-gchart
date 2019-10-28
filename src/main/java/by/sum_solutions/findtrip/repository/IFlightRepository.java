package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.FlightEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends CrudRepository<FlightEntity, Long> {

}
