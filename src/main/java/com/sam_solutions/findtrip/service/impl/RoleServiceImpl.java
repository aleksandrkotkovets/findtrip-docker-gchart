package com.sam_solutions.findtrip.service.impl;

import com.sam_solutions.findtrip.controller.dto.RoleDTO;
import com.sam_solutions.findtrip.controller.dto.UserDTO;
import com.sam_solutions.findtrip.repository.RoleRepository;
import com.sam_solutions.findtrip.repository.entity.RoleEntity;
import com.sam_solutions.findtrip.repository.entity.UserEntity;
import com.sam_solutions.findtrip.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> findAllRoles() {
        List<RoleEntity> rolesEntity = roleRepository.findAll();
        return rolesEntity.stream().map(a -> new RoleDTO(a.getId(), a.getRole())).collect(Collectors.toList());
    }

    @Override
    public RoleEntity findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {
        RoleEntity roleEntity = roleRepository.findByRole(role);
        List<UserEntity> users = roleEntity.getUsers();
        List<UserDTO> userDTOList = users.stream()
                .map(a -> new UserDTO(
                        a.getId(),
                        a.getLogin(),
                        a.getPassword(),
                        a.getEmail(),
                        a.getFirstName(),
                        a.getLastName(),
                        a.getPatronymic(),
                        a.getPhoneNumber(),
                        a.getRoleEntity().getRole()
                ))
                .collect(Collectors.toList());

        return userDTOList;
    }
}
