package by.sum_solutions.findtrip.service.impl;

import by.sum_solutions.findtrip.controller.dto.CityDTO;
import by.sum_solutions.findtrip.controller.dto.CountryDTO;
import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.exception.UserNotFoundException;
import by.sum_solutions.findtrip.repository.CountryRepository;
import by.sum_solutions.findtrip.repository.entity.CityEntity;
import by.sum_solutions.findtrip.repository.entity.CountryEntity;
import by.sum_solutions.findtrip.repository.entity.UserEntity;
import by.sum_solutions.findtrip.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;


    @Transactional
    @Override
    public Page<CountryEntity> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CountryDTO findOne(Long id) {
        Optional<CountryEntity> countryEntity=  countryRepository.findById(id);
        if(countryEntity.isPresent()){
            CountryDTO countryDTO = new CountryDTO();
            CityDTO cityDTO;
            countryDTO.setName(countryEntity.get().getName());
            countryDTO.setId(countryEntity.get().getId());

            List<CityDTO> cityDTOList = new ArrayList<>();
                for(CityEntity cityEntity : countryEntity.get().getCities()){
                    cityDTO = new CityDTO();
                    cityDTO.setId(cityEntity.getId());
                    cityDTO.setName(cityEntity.getName());
                    cityDTOList.add(cityDTO);
                }
                countryDTO.setCityDTOList(cityDTOList);
            return countryDTO;
        }else {
            return null;
        }
    }

    @Transactional
    @Override
    public Long getCountryIdByName(String name) {
        return countryRepository.getIdExistCountryByName(name);
    }

    @Transactional
    @Override
    public void saveOrUpdate(CountryDTO countryDTO) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(countryDTO.getId());
        countryEntity.setName(countryDTO.getName());

        if(countryEntity.getId() == null){
            countryRepository.save(countryEntity);
        }else {
            CountryEntity editCountryEntity;
            if( countryRepository.findById(countryEntity.getId()).isPresent()){
                editCountryEntity = countryRepository.findById(countryEntity.getId()).get();
                editCountryEntity.setId(countryEntity.getId());
                editCountryEntity.setName(countryEntity.getName());
                countryRepository.save(editCountryEntity);
            }else {
                throw new UserNotFoundException("Country with id="+countryEntity.getId()+" not found");
            }

        }
    }

    @Transactional
    @Override
    public CountryDTO findCountryByName(String name) {
        Optional<CountryEntity> countryEntity=  countryRepository.findByName(name);
        if(countryEntity.isPresent()){
            CountryDTO countryDTO = new CountryDTO();
            CityDTO cityDTO;
            countryDTO.setName(countryEntity.get().getName());
            countryDTO.setId(countryEntity.get().getId());

            List<CityDTO> cityDTOList = new ArrayList<>();
            for(CityEntity cityEntity : countryEntity.get().getCities()){
                cityDTO = new CityDTO();
                cityDTO.setId(cityEntity.getId());
                cityDTO.setName(cityEntity.getName());
                cityDTOList.add(cityDTO);
            }
            countryDTO.setCityDTOList(cityDTOList);
            return countryDTO;
        }else {
            return null;
        }
    }


}
