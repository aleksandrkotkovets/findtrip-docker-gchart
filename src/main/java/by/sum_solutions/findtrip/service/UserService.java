package by.sum_solutions.findtrip.service;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity save(UserDTO userDTO);

    UserEntity getUserByCriteria(String email, String login, String phoneNumber);

    List<UserDTO> getUsersByRole(Role role);

    void deleteUserById(Long id);

    UserDTO findUserById(Long id);

    void update(UserDTO user);

    boolean findUserByCriteria(Optional<String> login, Optional<String> password);
}
