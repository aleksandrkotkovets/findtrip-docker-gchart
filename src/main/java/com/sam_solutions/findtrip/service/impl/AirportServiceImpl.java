package com.sam_solutions.findtrip.service.impl;

import com.sam_solutions.findtrip.controller.dto.AirportDTO;
import com.sam_solutions.findtrip.controller.dto.CityDTO;
import com.sam_solutions.findtrip.controller.dto.CountryDTO;
import com.sam_solutions.findtrip.exception.AirportAddParameterExistException;
import com.sam_solutions.findtrip.exception.EditAirportParametersExistException;
import com.sam_solutions.findtrip.repository.AirportRepository;
import com.sam_solutions.findtrip.repository.CityRepository;
import com.sam_solutions.findtrip.repository.entity.AirportEntity;
import com.sam_solutions.findtrip.repository.entity.CityEntity;
import com.sam_solutions.findtrip.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private AirportRepository airportRepository;
    private CityRepository cityRepository;

    @Autowired

    public AirportServiceImpl(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<AirportDTO> findAll() {
        List<AirportEntity> airportEntities = airportRepository.findAll();
        airportEntities.sort(Comparator.comparing(o -> o.getCityEntity().getCountryEntity().getName()));

        List<AirportDTO> airportDTOs = new ArrayList<>();

        for (AirportEntity airportEntity : airportEntities) {

            AirportDTO airportDTO = new AirportDTO(airportEntity.getId(), airportEntity.getName(), airportEntity.getCode());
            CityDTO cityDTO = new CityDTO(airportEntity.getCityEntity().getId(), airportEntity.getCityEntity().getName());
            CountryDTO countryDTO = new CountryDTO(airportEntity.getCityEntity().getCountryEntity().getId(), airportEntity.getCityEntity().getCountryEntity().getName());

            cityDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTO);

            airportDTOs.add(airportDTO);
        }
        return airportDTOs;
    }

    @Override
    public AirportDTO findById(Long id) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(id);
        AirportDTO airportDTO = new AirportDTO();
        if (airportEntity.isPresent()) {

            airportDTO.setId(airportEntity.get().getId());
            airportDTO.setName(airportEntity.get().getName());
            airportDTO.setCode(airportEntity.get().getCode());

            CityDTO cityDTO = new CityDTO();
            cityDTO.setId(airportEntity.get().getCityEntity().getId());
            cityDTO.setName(airportEntity.get().getCityEntity().getName());

            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setId(airportEntity.get().getCityEntity().getCountryEntity().getId());
            countryDTO.setName(airportEntity.get().getCityEntity().getCountryEntity().getName());

            cityDTO.setCountryDTO(countryDTO);
            airportDTO.setCityDTO(cityDTO);

        } else {
            throw new EntityNotFoundException("AirportEntity with id=" + id + " not found");
        }
        return airportDTO;
    }

    @Transactional
    @Override
    public void updateAirport(AirportDTO airportDTO) {

        if (airportDTO.getId() != null) {

            Optional<AirportEntity> airportEntity = airportRepository.findById(airportDTO.getId());

            if (airportEntity.isPresent()) {


                if (airportRepository.findIdByName(airportDTO.getName()) != airportEntity.get().getId() && airportRepository.findIdByName(airportDTO.getName()) != null) {
                    throw new EditAirportParametersExistException("Airport_with_this_name_already_exist", airportDTO);
                }

                if (airportRepository.findIdByCode(airportDTO.getCode()) != airportEntity.get().getId() && airportRepository.findIdByCode(airportDTO.getCode()) != null) {
                    throw new EditAirportParametersExistException("Airport_with_this_code_already_exist", airportDTO);
                }

                airportEntity.get().setName(airportDTO.getName());
                airportEntity.get().setCode(airportDTO.getCode());

                airportRepository.save(airportEntity.get());


            } else {
                throw new EntityNotFoundException("Airport with id=" + airportEntity.get().getId() + " not found");
            }

        } else {
            throw new EntityNotFoundException("Id == null");
        }

    }

    @Transactional
    @Override
    public void delete(Long id) {
        airportRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void save(AirportDTO airportDTO) {

        if (airportRepository.findIdByName(airportDTO.getName()) != null) {
            throw new AirportAddParameterExistException("Airport_with_this_name_already_exist", airportDTO);
        }

        if (airportRepository.findIdByCode(airportDTO.getCode()) != null) {
            throw new AirportAddParameterExistException("Airport_with_this_code_already_exist", airportDTO);
        }

        CityEntity cityEntity = cityRepository.findById(airportDTO.getCityDTO().getId()).get();
        AirportEntity airportEntity = new AirportEntity();
        airportEntity.setName(airportDTO.getName());
        airportEntity.setCode(airportDTO.getCode());
        airportEntity.setCityEntity(cityEntity);
        airportRepository.save(airportEntity);
    }
}
