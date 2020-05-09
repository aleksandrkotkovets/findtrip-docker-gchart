package com.sam_solutions.findtrip.schedule;

import com.sam_solutions.findtrip.repository.FlightRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class Scheduled {

    private final FlightRepository flightRepository;

    public Scheduled(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Update every 5 minutes
     */
    @Transactional
    @org.springframework.scheduling.annotation.Scheduled(fixedDelay = 300000)
    public void setFlightStatusFinished() {
        flightRepository.setFlightStatusFinished(new Date());
    }

}
