package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.exception.EditUsersParametersExistException;
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
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Transactional
    @Override
    public UserEntity save(UserDTO userDTO, String role) {
        Long idExistUser = userRepository.getIdUserByEmail(userDTO.getEmail());
        if ( idExistUser != null) {
            throw new EditUsersParametersExistException("User_with_this_email_already_exist", userDTO);
        }

        idExistUser = userRepository.getIdUserByLogin(userDTO.getLogin());
        if (idExistUser != null) {
            throw new EditUsersParametersExistException("This_login_is_exist", userDTO);
        }

        idExistUser = userRepository.getIdUserByPhoneNumber(userDTO.getPhoneNumber());
        if (idExistUser != null) {
            throw new EditUsersParametersExistException("This_phone_number_already_exist",userDTO);
        }

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
    public List<UserDTO> getUsersByRole(String role) {

        List<UserEntity> userEntityList = userRepository.findAllByRoleEntity_Role(role);
        List<UserDTO> userDTOList = userEntityList.stream()
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

        Long idExistUser = userRepository.getIdUserByEmail(user.getEmail());

        if (idExistUser != null &&  !idExistUser.equals(user.getId())) {
            throw new EditUsersParametersExistException("User_with_this_email_already_exist", user);
        }

        idExistUser = userRepository.getIdUserByLogin(user.getLogin());
        if (idExistUser != null && !idExistUser.equals(user.getId())) {
            throw new EditUsersParametersExistException("This_login_is_exist", user);
        }

        idExistUser = userRepository.getIdUserByPhoneNumber(user.getPhoneNumber());
        if (idExistUser != null && !idExistUser.equals(user.getId())) {
            throw new EditUsersParametersExistException("This_phone_number_already_exist", user);
        }

        Optional<UserEntity> editUserEntity = userRepository.findById(user.getId());
        if (editUserEntity.isPresent()) {

            editUserEntity.get().setLogin(user.getLogin());
            editUserEntity.get().setPassword(user.getPassword());
            editUserEntity.get().setEmail(user.getEmail());
            editUserEntity.get().setFirstName(user.getFirstName());
            editUserEntity.get().setLastName(user.getFirstName());
            editUserEntity.get().setPatronymic(user.getPatronymic());
            editUserEntity.get().setPhoneNumber(user.getPhoneNumber());

            userRepository.save(editUserEntity.get());
        } else {
            throw new UserNotFoundException("User with id=" + user.getId() + " not found");
        }

    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

}