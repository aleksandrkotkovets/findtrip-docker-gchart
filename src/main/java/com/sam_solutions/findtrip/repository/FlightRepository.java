package com.sam_solutions.findtrip.repository;

import com.sam_solutions.findtrip.controller.dto.CityFrAndTo;
import com.sam_solutions.findtrip.repository.entity.FlightEntity;
import com.sam_solutions.findtrip.repository.entity.FlightStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface FlightRepository extends JpaRepository<FlightEntity, Long>, JpaSpecificationExecutor<FlightEntity> {

    List<FlightEntity> findAllByStatus(FlightStatus status, Sort departureDate);

    @Query("SELECT NEW com.sam_solutions.findtrip.controller.dto.CityFrAndTo(fl.airportArrival.cityEntity.id,fl.airportDeparture.cityEntity.id) " +
            "FROM FlightEntity fl WHERE (fl.status = ?1 OR fl.status=?2)")
    Set<CityFrAndTo> findCityFrAndToAndSort(FlightStatus status1, FlightStatus status2);

    @Modifying
    @Query( "FROM FlightEntity fl " +
            "WHERE (fl.status = com.sam_solutions.findtrip.repository.entity.FlightStatus.ACTIVE AND fl.arrivalDate<:now)")
    List<FlightEntity> findAllByStatusActiveAndArrivalDateBefore(Date now);

}
