package com.sam_solutions.findtrip.repository;

import com.sam_solutions.findtrip.repository.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AirportRepository extends JpaRepository<AirportEntity, Long> {

    @Query(value = "SELECT ap.id FROM AirportEntity ap WHERE ap.name = ?1")
    Long findIdByName(String name);

    @Query(value = "SELECT ap.id FROM AirportEntity ap WHERE ap.code = ?1")
    Long findIdByCode(String code);

}
