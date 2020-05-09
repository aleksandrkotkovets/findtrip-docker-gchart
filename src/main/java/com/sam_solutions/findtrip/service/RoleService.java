package com.sam_solutions.findtrip.service;


import com.sam_solutions.findtrip.controller.dto.RoleDTO;
import com.sam_solutions.findtrip.controller.dto.UserDTO;
import com.sam_solutions.findtrip.repository.entity.RoleEntity;

import java.util.List;

public interface RoleService {


    List<RoleDTO> findAllRoles();

    RoleEntity findByRole(String role);

    List<UserDTO> getUsersByRole(String role);
}
