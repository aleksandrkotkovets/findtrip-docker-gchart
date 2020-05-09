package com.sam_solutions.findtrip.repository;

import com.sam_solutions.findtrip.repository.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

    WalletEntity findByOwnerId(Long id);
}
