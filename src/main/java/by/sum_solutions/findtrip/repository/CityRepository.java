package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<CityEntity, Long > {

    void deleteById(Long id);

    @Query(
            value = "SELECT city.id FROM city city WHERE city.name = ?1",
            nativeQuery = true)
    Long getIdExistCityByName(String name);
}
