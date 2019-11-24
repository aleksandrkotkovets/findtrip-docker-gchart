package by.sum_solutions.findtrip.service.impl;

import by.sum_solutions.findtrip.controller.dto.RoleDTO;
import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.repository.RoleRepository;
import by.sum_solutions.findtrip.repository.entity.RoleEntity;
import by.sum_solutions.findtrip.repository.entity.UserEntity;
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
        return roleRepository.findByRole(role);
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {
       RoleEntity roleEntity = roleRepository.findByRole(role);
       List<UserEntity> users = roleEntity.getUsers();
       List<UserDTO> userDTOList = new ArrayList<>();
       final UserDTO[] userDTO = new UserDTO[1];
       users.stream().forEach(a-> {
           userDTO[0] = new UserDTO();
           userDTO[0].setId(a.getId());
           userDTO[0].setEmail(a.getEmail());
           userDTO[0].setLogin(a.getLogin());
           userDTO[0].setPassword(a.getPassword());
           userDTO[0].setFirstName(a.getFirstName());
           userDTO[0].setLastName(a.getLastName());
           userDTO[0].setPatronymic(a.getPatronymic());
           userDTO[0].setPhoneNumber(a.getPhoneNumber());
           userDTO[0].setRole(a.getRoleEntity().getRole());
           userDTOList.add(userDTO[0]);
       });
        /*for (UserEntity entity : users) {
            userDTO = new UserDTO();
            userDTO.setId(entity.getId());
            userDTO.setEmail(entity.getEmail());
            userDTO.setLogin(entity.getLogin());
            userDTO.setPassword(entity.getPassword());
            userDTO.setFirstName(entity.getFirstName());
            userDTO.setLastName(entity.getLastName());
            userDTO.setPatronymic(entity.getPatronymic());
            userDTO.setPhoneNumber(entity.getPhoneNumber());
            userDTO.setRole(entity.getRoleEntity().getRole());
            userDTOList.add(userDTO);
        }*/

       return userDTOList;
    }
}
