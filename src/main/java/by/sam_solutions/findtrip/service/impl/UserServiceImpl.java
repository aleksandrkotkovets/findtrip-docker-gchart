package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.exception.UserNotFoundException;
import by.sam_solutions.findtrip.repository.UserRepository;
import by.sam_solutions.findtrip.repository.entity.UserEntity;
import by.sam_solutions.findtrip.service.RoleService;
import by.sam_solutions.findtrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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



    @Override
    public Long getUserByCriteria(String email, String login, String phoneNumber) {

        Long id = null;

        if (email != null && !email.equals("")) {
            id = userRepository.getIdExistUserByEmail(email);
        }

        if (login != null && !login.equals("")) {
            id = userRepository.getIdExistUserByLogin(login);
        }

        if (phoneNumber != null && !phoneNumber.equals("")) {
            id = userRepository.getIdExistUserByPhoneNumber(phoneNumber);
        }

        return id;
    }


    @Override
    public List<UserDTO> getUsersByRole(String  role) {

        List<UserEntity> userEntityList = userRepository.findAllByRoleEntity_Role(role);
        List<UserDTO> userDTOList = userEntityList.stream()
                .map(a-> new UserDTO(
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


    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with id=" + id + " not found");
        }
    }


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


    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

}