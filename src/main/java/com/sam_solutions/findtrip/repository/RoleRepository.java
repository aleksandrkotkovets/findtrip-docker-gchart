package com.sam_solutions.findtrip.repository;


import com.sam_solutions.findtrip.repository.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

        RoleEntity findByRole(String role);

        List<RoleEntity> findAll();


}
