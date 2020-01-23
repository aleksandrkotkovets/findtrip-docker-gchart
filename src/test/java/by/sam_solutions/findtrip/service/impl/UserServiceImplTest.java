package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.repository.RoleRepository;
import by.sam_solutions.findtrip.repository.UserRepository;
import by.sam_solutions.findtrip.repository.entity.UserEntity;
import by.sam_solutions.findtrip.service.FakeUserDB;
import by.sam_solutions.findtrip.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    private FakeUserDB fakeUserDB = new FakeUserDB();

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void save() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void findUserById() {
       /* UserEntity userEntity = fakeUserDB.getById(1);

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity));
        verify(userRepository).findById(1L);*/
    }

    @Test
    void findByLogin() {
    }
}