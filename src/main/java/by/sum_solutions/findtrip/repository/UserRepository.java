package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.RoleEntity;
import by.sum_solutions.findtrip.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    @Query(
            value = "SELECT u.id FROM app_user u WHERE u.login = ?1",
            nativeQuery = true)
    Long getIdExistUserByLogin(String login);

    @Query(
            value = "SELECT u.id FROM app_user u WHERE u.email = ?1",
            nativeQuery = true)
    Long getIdExistUserByEmail(String email);

    @Query(
            value = "SELECT u.id FROM app_user u WHERE u.phone_number = ?1",
            nativeQuery = true)
    Long getIdExistUserByPhoneNumber(String phoneNumber);


    List<UserEntity> findAllByRoleEntity_Role(String role);

    void deleteById(Long id);

    Optional<UserEntity> findById(Long id);

    UserEntity findByLogin(String login);
}
