package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    void deleteById(Long id);

    Optional<CountryEntity> findById(Long id);

    @Query(value = "SELECT coun.id FROM country coun WHERE coun.name = ?1",nativeQuery = true)
    Long getIdExistCountryByName(String name);

   Optional<CountryEntity> findByName(String name);
}
