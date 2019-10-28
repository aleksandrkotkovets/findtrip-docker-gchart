package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.PlainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlainRepository extends JpaRepository<PlainEntity, Long> {

}
