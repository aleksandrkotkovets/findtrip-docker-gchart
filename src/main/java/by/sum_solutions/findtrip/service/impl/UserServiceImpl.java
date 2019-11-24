package by.sum_solutions.findtrip.service.impl;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.exception.UserNotFoundException;
import by.sum_solutions.findtrip.repository.RoleRepository;
import by.sum_solutions.findtrip.repository.UserRepository;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.repository.entity.RoleEntity;
import by.sum_solutions.findtrip.repository.entity.UserEntity;
import by.sum_solutions.findtrip.service.RoleService;
import by.sum_solutions.findtrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Transactional
    @Override
    public UserEntity save(UserDTO userDTO, String role) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPatronymic(userDTO.getPatronymic());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setRoleEntity(roleService.findByRole(role));
       return userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public UserEntity getUserByCriteria(String email, String login, String phoneNumber) {

        UserEntity userEntity = null;

        if (email != null && !email.equals("")) {
            userEntity = userRepository.findUserEntityByEmail(email);
        }

        if (login != null && !login.equals("")) {
            userEntity = userRepository.findUserEntityByLogin(login);
        }

        if (phoneNumber != null && !phoneNumber.equals("")) {
            userEntity = userRepository.findUserEntityByPhoneNumber(phoneNumber);
        }

        return userEntity;
    }

    @Transactional
    @Override
    public List<UserDTO> getUsersByRole(String  role) {
        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO userDTO;
        List<UserEntity> userEntityList = userRepository.findAllByRoleEntity_Role(role);

        for (UserEntity entity : userEntityList) {
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
        }
        return userDTOList;

    }

    @Transactional
    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with id=" + id + " not found");
        }
    }

    @Transactional
    @Override
    public UserDTO findUserById(Long id) {
        UserDTO userDTO = null;
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            userDTO = new UserDTO();
            userDTO.setId(userEntity.get().getId());
            userDTO.setEmail(userEntity.get().getEmail());
            userDTO.setLogin(userEntity.get().getLogin());
            userDTO.setPassword(userEntity.get().getPassword());
            userDTO.setFirstName(userEntity.get().getFirstName());
            userDTO.setLastName(userEntity.get().getLastName());
            userDTO.setPatronymic(userEntity.get().getPatronymic());
            userDTO.setPhoneNumber(userEntity.get().getPhoneNumber());
            userDTO.setRole(userEntity.get().getRoleEntity().getRole());
        }
        return userDTO;
    }

    @Transactional
    @Override
    public void update(UserDTO user) {
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setId(user.getId());
        newUserEntity.setLogin(user.getLogin());
        newUserEntity.setPassword(user.getPassword());
        newUserEntity.setEmail(user.getEmail());
        newUserEntity.setFirstName(user.getFirstName());
        newUserEntity.setLastName(user.getFirstName());
        newUserEntity.setPatronymic(user.getPatronymic());
        newUserEntity.setPhoneNumber(user.getPhoneNumber());
        newUserEntity.setRoleEntity(roleService.findByRole(user.getRole()));

        if(newUserEntity.getId() == null){
            userRepository.save(newUserEntity);
        }else {
            UserEntity editUserEntity = new UserEntity();
           if( userRepository.findById(newUserEntity.getId()).isPresent()){
               editUserEntity = userRepository.findById(newUserEntity.getId()).get();
               editUserEntity.setId(newUserEntity.getId());
               editUserEntity.setLogin(newUserEntity.getLogin());
               editUserEntity.setPassword(newUserEntity.getPassword());
               editUserEntity.setEmail(newUserEntity.getEmail());
               editUserEntity.setFirstName(newUserEntity.getFirstName());
               editUserEntity.setLastName(newUserEntity.getFirstName());
               editUserEntity.setPatronymic(newUserEntity.getPatronymic());
               editUserEntity.setPhoneNumber(newUserEntity.getPhoneNumber());
               editUserEntity.setRoleEntity(newUserEntity.getRoleEntity());
               userRepository.save(editUserEntity);
           }else {
               throw new UserNotFoundException("User with id="+newUserEntity.getId()+" not found");
           }

        }
    }

    @Transactional
    @Override
    public boolean findUserByCriteria(Optional<String> login, Optional<String> password) {
        return false;
    }

    @Transactional
    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

}
