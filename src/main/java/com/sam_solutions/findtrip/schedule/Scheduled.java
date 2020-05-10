package com.sam_solutions.findtrip.schedule;

import com.sam_solutions.findtrip.repository.FlightRepository;
import com.sam_solutions.findtrip.repository.OrderRepository;
import com.sam_solutions.findtrip.repository.entity.FlightEntity;
import com.sam_solutions.findtrip.repository.entity.FlightStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class Scheduled {

    private final FlightRepository flightRepository;
    private final OrderRepository orderRepository;

    public Scheduled(FlightRepository flightRepository,
                     OrderRepository orderRepository) {
        this.flightRepository = flightRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Update every 5 minutes
     */
    @Transactional
    @org.springframework.scheduling.annotation.Scheduled(fixedDelay = 300000)
    public void setFlightStatusFinished() {
        List<FlightEntity> flightEntityList = flightRepository.findAllByStatusActiveAndArrivalDateBefore(new Date());

        if (flightEntityList.size() != 0) {
            flightEntityList.forEach(a -> orderRepository.setStatusFinishedWhereFlightId(a.getId()));
            flightEntityList.forEach(a->a.setStatus(FlightStatus.FINISHED));
        }

    }

}
