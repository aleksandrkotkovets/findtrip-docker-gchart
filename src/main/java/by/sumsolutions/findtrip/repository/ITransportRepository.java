package by.sumsolutions.findtrip.repository;

import by.sumsolutions.findtrip.repository.entity.TransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransportRepository extends JpaRepository<TransportEntity, Long> {

}
