package com.sam_solutions.findtrip.service.impl;

import com.sam_solutions.findtrip.controller.dto.ChartDto;
import com.sam_solutions.findtrip.repository.CityRepository;
import com.sam_solutions.findtrip.repository.FlightRepository;
import com.sam_solutions.findtrip.repository.entity.AirportEntity;
import com.sam_solutions.findtrip.repository.entity.FlightEntity;
import com.sam_solutions.findtrip.service.InfoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.sam_solutions.findtrip.repository.entity.FlightStatus.ACTIVE;
import static com.sam_solutions.findtrip.repository.entity.FlightStatus.FINISHED;

@Service
public class InfoServiceImpl implements InfoService {

    private final FlightRepository flightRepository;
    private final CityRepository cityRepository;

    public InfoServiceImpl(FlightRepository flightRepository,
                           CityRepository cityRepository) {
        this.flightRepository = flightRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<ChartDto> getDataForChart() {
        List<ChartDto> chartDtoList = new ArrayList<>();

        Optional<Set<CityFrAndTo>> dtoSet = getCityFrAndToSet();

        if (dtoSet.isPresent()) {

            ChartDto chartDto;
            for (CityFrAndTo cityFrAndTo : dtoSet.get()) {

                chartDto = new ChartDto();

                Example<FlightEntity> entityExample = Example.of(createFlightExample(cityFrAndTo));

                long count = flightRepository.count(entityExample);
                chartDto.setCountFlight(count);

                String cityFrName = cityRepository.findById(cityFrAndTo.getCityFr()).get().getName();
                String cityToName = cityRepository.findById(cityFrAndTo.getCityTo()).get().getName();
                chartDto.setFlightName(cityFrName + " - " + cityToName);

                chartDtoList.add(chartDto);
            }
        }
        return chartDtoList;
    }

    @NotNull
    private Optional<Set<CityFrAndTo>> getCityFrAndToSet() {
        Sort sort = Sort.by(
                Sort.Order.asc("airportArrival.cityEntity.id"),
                Sort.Order.asc("airportDeparture.cityEntity.id")
        );
        return Optional.of(flightRepository.findCityFrAndToAndSort(ACTIVE, FINISHED, sort));
    }

    @NotNull
    private FlightEntity createFlightExample(CityFrAndTo cityFrAndTo) {
        FlightEntity flightEntity = new FlightEntity();

        AirportEntity airportEntityArrival = new AirportEntity();
        airportEntityArrival.setCityEntity(cityRepository.findById(cityFrAndTo.getCityFr()).get());
        flightEntity.setAirportArrival(airportEntityArrival);

        AirportEntity airportEntityDepartures = new AirportEntity();
        airportEntityDepartures.setCityEntity(cityRepository.findById(cityFrAndTo.getCityTo()).get());
        flightEntity.setAirportDeparture(airportEntityDepartures);
        return flightEntity;
    }


}
