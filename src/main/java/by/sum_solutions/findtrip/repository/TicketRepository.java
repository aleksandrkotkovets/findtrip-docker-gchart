package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
