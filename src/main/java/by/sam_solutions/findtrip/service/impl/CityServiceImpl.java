package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.service.CityService;
import by.sam_solutions.findtrip.controller.dto.CityDTO;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.exception.UserNotFoundException;
import by.sam_solutions.findtrip.repository.CityRepository;
import by.sam_solutions.findtrip.repository.CountryRepository;
import by.sam_solutions.findtrip.repository.entity.CityEntity;
import by.sam_solutions.findtrip.repository.entity.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;


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

    @Override
    public List<CityDTO> getCityListByCountry(Long id) {
        List<CityEntity> cityEntities = cityRepository.findAllByCountryEntity_Id(id);
        List<CityDTO> cityDTOs = cityEntities.stream()
                .map(c -> new CityDTO(c.getId(), c.getName(),c.getCountryEntity().getId(),c.getCountryEntity().getName()))
                .collect(Collectors.toList());
        return cityDTOs;
    }

    @Override
    public Long getCountryIdByCityId(Long id) {
     return    cityRepository.getIdCountryByCityId(id);
    }

}
