package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AirportRepository extends JpaRepository<AirportEntity, Long> {

    @Query(value = "SELECT ap.id FROM airport ap WHERE ap.id = ?1",nativeQuery = true)
    Long findIdAirport(Long id);

    @Query(value = "SELECT airp.id FROM airport airp WHERE airp.name = ?1",nativeQuery = true)
    Long findIdByName(String name);

    @Query(value = "SELECT airp.id FROM airport airp WHERE airp.code = ?1",nativeQuery = true)
    Long findIdByCode(String code);

}
