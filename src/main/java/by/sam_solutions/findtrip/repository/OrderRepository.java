package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "select o.id from app_order o where o.flight_id=?1 and o.user_id=?2",nativeQuery = true)
    Long getOrderIdByFlightIdAndUserId(Long idFlight, Long idUser);

}
