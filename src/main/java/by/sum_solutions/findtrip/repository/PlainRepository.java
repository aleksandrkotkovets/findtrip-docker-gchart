package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.PlainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface PlainRepository extends JpaRepository<PlainEntity, Long> {


    Optional<PlainEntity> findById(Long id);

    PlainEntity findByName(String name);

    Set<PlainEntity> findAllByCompanyId(Long companyId);

    void delete(PlainEntity plainEntity);

    void deleteById(Long id);


}
