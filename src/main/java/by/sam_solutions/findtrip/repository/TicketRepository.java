package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    List<TicketEntity> findAllByOrderId(Long id);

    @Modifying
    @Query("delete from TicketEntity u where u.id = ?1")
    void deleteById(Long id);

}
