package by.sum_solutions.findtrip.service.impl;

import by.sum_solutions.findtrip.repository.entity.RoleEntity;
import by.sum_solutions.findtrip.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
//    RoleRepository roleRepository;


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
