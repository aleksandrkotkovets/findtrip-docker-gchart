package by.sum_solutions.findtrip.service;


import by.sum_solutions.findtrip.controller.dto.RoleDTO;
import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.repository.entity.RoleEntity;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Set<RoleEntity> findAll();

    List<RoleDTO> findAllRoles();

    RoleEntity findById(Long id);

    RoleEntity findByRole(String role);

    List<UserDTO> getUsersByRole(String role);
}
