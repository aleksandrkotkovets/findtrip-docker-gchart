package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
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



    @Query(value = "select p.id  from plane p where p.side_number=?1", nativeQuery = true)
    Long findIdBySideNumber(String sideNumber);

    @Query(value = "select p.company_id from plane p where p.id=?1",nativeQuery = true)
    Long getCompanyIdByPlaneId(Long id);
}
