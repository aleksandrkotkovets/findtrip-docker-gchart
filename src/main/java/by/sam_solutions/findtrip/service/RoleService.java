package by.sam_solutions.findtrip.service;


import by.sam_solutions.findtrip.controller.dto.RoleDTO;
import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.repository.entity.RoleEntity;

import java.util.List;
import java.util.Set;

public interface RoleService {


    List<RoleDTO> findAllRoles();

    RoleEntity findByRole(String role);

    List<UserDTO> getUsersByRole(String role);
}
