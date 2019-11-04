package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsUserEntityByLogin (String login);

    boolean existsUserEntityByEmail(String email);

    boolean existsUserEntityByPhoneNumber(String phoneNumber);


}
