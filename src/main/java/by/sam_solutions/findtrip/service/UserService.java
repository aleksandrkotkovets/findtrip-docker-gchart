package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity save(UserDTO userDTO, String role);

    List<UserDTO> getUsersByRole(String role);

    void deleteUserById(Long id);

    UserDTO findUserById(Long id);

    void update(UserDTO user);

    Optional<UserEntity> findByLogin(String login);
}
