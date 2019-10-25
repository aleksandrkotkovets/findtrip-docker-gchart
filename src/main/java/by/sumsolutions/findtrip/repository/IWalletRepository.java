package by.sumsolutions.findtrip.repository;

import by.sumsolutions.findtrip.repository.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWalletRepository extends JpaRepository<WalletEntity, Long> {

}
