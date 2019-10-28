package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.PlainEntity;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IPlainRepository extends CrudRepository<PlainEntity, Long> {


    Optional<PlainEntity> findById(Long id);

    PlainEntity findByName(String name);

    Set<PlainEntity> findAllByCompanyId(Long companyId);

    void delete(PlainEntity plainEntity);

    void deleteById(Long id);


}
