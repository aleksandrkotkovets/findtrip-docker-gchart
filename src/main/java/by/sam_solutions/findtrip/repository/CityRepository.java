package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long > {

    void deleteById(Long id);

    @Query(
            value = "SELECT city.id FROM city city WHERE city.name = ?1",
            nativeQuery = true)
    Long getIdExistCityByName(String name);

    List<CityEntity> findAllByCountryEntity_Id(Long id);

    @Query(value = "select ct.country_id from city ct where ct.id=?1", nativeQuery = true)
    Long getIdCountryByCityId(Long id);
}
