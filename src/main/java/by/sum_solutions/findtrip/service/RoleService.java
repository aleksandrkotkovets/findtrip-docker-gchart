package by.sum_solutions.findtrip.service;


import by.sum_solutions.findtrip.repository.entity.RoleEntity;

import java.util.Set;

public interface RoleService {

    Set<RoleEntity> findAll();

    RoleEntity findById(Long id);

    RoleEntity findByRole(String role);

}
