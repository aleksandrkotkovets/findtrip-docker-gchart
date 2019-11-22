package by.sum_solutions.findtrip.service.impl;

import by.sum_solutions.findtrip.controller.dto.RoleDTO;
import by.sum_solutions.findtrip.repository.RoleRepository;
import by.sum_solutions.findtrip.repository.entity.RoleEntity;
import by.sum_solutions.findtrip.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleDTO> findAllRoles() {
        List<RoleEntity> rolesEntity = roleRepository.findAll();
        List<RoleDTO> rolesDTO = new ArrayList<>();
        RoleDTO roleDTO;
        for(RoleEntity entity : rolesEntity){
            roleDTO = new RoleDTO();
            roleDTO.setId(entity.getId());
            roleDTO.setRole(entity.getRole());
            rolesDTO.add(roleDTO);
        }
        return rolesDTO;
    }

    @Override
    public Set<RoleEntity> findAll() {
        return null;
    }



    @Override
    public RoleEntity findById(Long id) {
        return null;
    }

    @Override
    public RoleEntity findByRole(String role) {
        return null;
    }
}
