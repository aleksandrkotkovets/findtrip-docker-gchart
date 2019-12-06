package by.sum_solutions.findtrip.service.impl;

import by.sum_solutions.findtrip.controller.CountryController;
import by.sum_solutions.findtrip.controller.dto.CityDTO;
import by.sum_solutions.findtrip.controller.dto.CountryDTO;
import by.sum_solutions.findtrip.exception.UserNotFoundException;
import by.sum_solutions.findtrip.repository.CityRepository;
import by.sum_solutions.findtrip.repository.CountryRepository;
import by.sum_solutions.findtrip.repository.entity.CityEntity;
import by.sum_solutions.findtrip.repository.entity.CountryEntity;
import by.sum_solutions.findtrip.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Transactional
    @Override
    public CityDTO findOne(Long id) {
        Optional<CityEntity> cityEntity=  cityRepository.findById(id);
        if(cityEntity.isPresent()){
            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(cityEntity.get().getName());
            cityDTO.setId(cityEntity.get().getId());

            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(cityEntity.get().getCountryEntity().getName());
            countryDTO.setId(cityEntity.get().getCountryEntity().getId());
            cityDTO.setCountryDTO(countryDTO);
            return cityDTO;
        }else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Long getCityIdByName(String name) {

        return cityRepository.getIdExistCityByName(name);
    }

    @Transactional
    @Override
    public void saveOrUpdate(CityDTO cityDTO, String countryName) {


        if(cityDTO.getId() == null){
            CityEntity cityEntity = new CityEntity();
            cityEntity.setId(cityDTO.getId());
            cityEntity.setName(cityDTO.getName());

            Optional<CountryEntity> countryEntity = countryRepository.findByName(countryName);
            cityEntity.setCountryEntity(countryEntity.get());

            cityRepository.save(cityEntity);
        }else {
            CityEntity editCityEntity;
            if( cityRepository.findById(cityDTO.getId()).isPresent()){
                editCityEntity = cityRepository.findById(cityDTO.getId()).get();
                editCityEntity.setName(cityDTO.getName());
                cityRepository.save(editCityEntity);
            }else {
                throw new UserNotFoundException("City with id="+cityDTO.getId()+" not found");
            }

        }
    }

}
