package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    @Query(value = "SELECT u.id FROM UserEntity u WHERE u.login = ?1")
    Long getIdExistUserByLogin(String login);

    @Query(value = "SELECT u.id FROM UserEntity u WHERE u.email = ?1")
    Long getIdExistUserByEmail(String email);

    @Query(value = "SELECT u.id FROM UserEntity u WHERE u.phoneNumber = ?1")
    Long getIdExistUserByPhoneNumber(String phoneNumber);

    List<UserEntity> findAllByRoleEntity_Role(String role);

    void deleteById(Long id);

    Optional<UserEntity> findById(Long id);

    UserEntity findByLogin(String login);
}
