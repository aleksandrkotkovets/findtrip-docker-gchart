package by.sumsolutions.findtrip.repository;

import by.sumsolutions.findtrip.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
