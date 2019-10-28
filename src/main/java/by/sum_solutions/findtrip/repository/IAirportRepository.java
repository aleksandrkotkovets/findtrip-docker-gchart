package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirportRepository extends CrudRepository<AirportEntity, Long> {

}
