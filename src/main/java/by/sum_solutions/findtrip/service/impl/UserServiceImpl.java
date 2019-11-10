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
    public UserEntity getUserByCriteria(String email, String login, String phoneNumber){

        UserEntity userEntity = null;

        if(email != null && !email.equals("")){
            userEntity = userRepository.findUserEntityByEmail(email);
        }

        if(login !=null && !login.equals("")){
            userEntity = userRepository.findUserEntityByLogin(login);
        }

        if(phoneNumber != null && !phoneNumber.equals("")){
            userEntity = userRepository.findUserEntityByPhoneNumber(phoneNumber);
        }

        return userEntity;
    }


}
