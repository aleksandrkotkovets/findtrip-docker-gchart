package by.sumsolutions.findtrip.repository;

import by.sumsolutions.findtrip.repository.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<CityEntity, Long> {
}
