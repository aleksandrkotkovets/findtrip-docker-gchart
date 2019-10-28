package by.sumsolutions.findtrip.repository;

import by.sumsolutions.findtrip.repository.entity.PlainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlainRepository extends JpaRepository<PlainEntity, Long> {

}
