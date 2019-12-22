package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    @Query(value = "select t.id from ticket t where t.flight_id=?1 and t.user_id=?2",nativeQuery = true)
    Long getTicketIdByFlightIdAndUserId(Long idFlight, Long idUser);

}
