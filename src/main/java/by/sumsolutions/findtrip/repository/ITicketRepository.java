package by.sumsolutions.findtrip.repository;

import by.sumsolutions.findtrip.repository.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ITicketRepository extends JpaRepository<TicketEntity, Long> {
}
