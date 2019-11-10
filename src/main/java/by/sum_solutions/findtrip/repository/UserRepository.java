package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityByEmail(String email);

    UserEntity findUserEntityByLogin(String login);

    UserEntity findUserEntityByPhoneNumber(String phoneNumber);

    List<UserEntity> findAllByRole(Role role);

    void deleteUserEntityById(Long id);

}
