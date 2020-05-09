package com.sam_solutions.findtrip.repository;

import com.sam_solutions.findtrip.repository.entity.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface PlaneRepository extends JpaRepository<PlaneEntity, Long> {

    Optional<PlaneEntity> findById(Long id);

    PlaneEntity findByName(String name);

    Set<PlaneEntity> findAllByCompanyId(Long companyId);

    void delete(PlaneEntity plainEntity);

    void deleteById(Long id);

    @Query(value = "select p.id  from PlaneEntity p where p.sideNumber=?1")
    Long findIdBySideNumber(String sideNumber);

    @Query(value = "select p.company.id from PlaneEntity p where p.id=?1")
    Long getCompanyIdByPlaneId(Long id);
}
