package by.sum_solutions.findtrip.service.impl;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.repository.UserRepository;
import by.sum_solutions.findtrip.repository.entity.UserEntity;
import by.sum_solutions.findtrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPatronymic(userDTO.getPatronymic());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setRole(userDTO.getRole());
        return userRepository.save(userEntity);
    }

    @Override
    public String isUserExistByCriteria(String email, String login, String phoneNumber){
        if(userRepository.existsUserEntityByLogin(login)){
            return "Login already exist";
        }
        if(userRepository.existsUserEntityByEmail(email)){
            return "Email already exist";
        }
        if(userRepository.existsUserEntityByPhoneNumber(phoneNumber)){
            return "Phone number already exist";
        }

        return null;
    }

    @Override
    public String existsUserByLogin(String login){
        return userRepository.existsUserEntityByLogin(login) ? "Login already exist" : null;
    }

    @Override
    public String existsUserByEmail(String email){
        return userRepository.existsUserEntityByEmail(email) ? "Email already exist" : null;
    }

    @Override
    public String existsUserByPhoneNumber(String phoneNumber){
        return userRepository.existsUserEntityByPhoneNumber(phoneNumber) ? "Phone number already exist" : null;
    }

}
