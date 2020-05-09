package com.sam_solutions.findtrip.schedule;

import com.sam_solutions.findtrip.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class Scheduled {

    private final FlightRepository flightRepository;

    public Scheduled(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    @org.springframework.scheduling.annotation.Scheduled(fixedDelay = 300000)
    public void setFlightStatusFinished() {
        flightRepository.setFlightStatusFinished(new Date());
    }

}
