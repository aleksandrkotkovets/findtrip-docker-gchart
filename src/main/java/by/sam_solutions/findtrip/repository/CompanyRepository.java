package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.CompanyEntity;
import by.sam_solutions.findtrip.repository.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findById(Long id);

    List<CompanyEntity> findAll();

    CompanyEntity findByName(String name);

    Set<CompanyEntity> findByRating(Rating rating);

    CompanyEntity findByNameAndRating(String name, Rating rating);

    void delete(CompanyEntity companyEntity);

    void deleteById(Long id);

    @Query(value = "SELECT comp.id FROM company comp WHERE comp.name = ?1",nativeQuery = true)
    Long getCompanyIdByName(String name);

    @Query(value = "select * from company comp order by comp.name asc", nativeQuery = true)
    List<CompanyEntity> findAllAndOrderByName();
}
