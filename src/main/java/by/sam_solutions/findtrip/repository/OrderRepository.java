package by.sam_solutions.findtrip.repository;

import by.sam_solutions.findtrip.repository.entity.OrderEntity;
import by.sam_solutions.findtrip.repository.entity.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "select o.id from OrderEntity o where o.flight.id=?1 and o.user.id=?2")
    Long getOrderIdByFlightIdAndUserId(Long idFlight, Long idUser);

    List<OrderEntity> findAllByUserId(Long id);

    List<OrderEntity> findAllByFlightId(Long id);

    List<OrderEntity> findAllByUserIdAndStatus(Long id, OrderStatus status, Sort sort);
}
