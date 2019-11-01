package by.sum_solutions.findtrip.service;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.repository.entity.UserEntity;

public interface UserService {

    UserEntity save(UserDTO userDTO);
}
