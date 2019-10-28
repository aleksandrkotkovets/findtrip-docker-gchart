package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.WalletEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepository extends CrudRepository<WalletEntity, Long> {

}
