package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface PlaneRepository extends JpaRepository<PlaneEntity, Long> {


    Optional<PlaneEntity> findById(Long id);

    PlaneEntity findByName(String name);

    Set<PlaneEntity> findAllByCompanyId(Long companyId);

    void delete(PlaneEntity plainEntity);

    void deleteById(Long id);


}
