package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.CityDTO;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.exception.UserNotFoundException;
import by.sam_solutions.findtrip.repository.CountryRepository;
import by.sam_solutions.findtrip.repository.entity.CityEntity;
import by.sam_solutions.findtrip.repository.entity.CountryEntity;
import by.sam_solutions.findtrip.service.CountryService;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    @Override
    public CountryDTO findOne(Long id) {
        Optional<CountryEntity> countryEntity = countryRepository.findById(id);
        return mapToCountryDTO(countryEntity);
    }

    @Nullable
    private CountryDTO mapToCountryDTO(Optional<CountryEntity> countryEntity) {
        if (countryEntity.isPresent()) {
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(countryEntity.get().getName());
            countryDTO.setId(countryEntity.get().getId());

            List<CityDTO> cityDTOList = countryEntity.get().getCities().stream().map(a -> new CityDTO(a.getId(), a.getName())).collect(Collectors.toList());
            countryDTO.setCityDTOList(cityDTOList);

            return countryDTO;
        } else {
            return null;
        }
    }


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

        if (countryEntity.getId() == null) {
            countryRepository.save(countryEntity);
        } else {
            CountryEntity editCountryEntity;
            if (countryRepository.findById(countryEntity.getId()).isPresent()) {
                editCountryEntity = countryRepository.findById(countryEntity.getId()).get();
                editCountryEntity.setName(countryEntity.getName());
                countryRepository.save(editCountryEntity);
            } else {
                throw new UserNotFoundException("Country with id=" + countryEntity.getId() + " not found");
            }

        }
    }


    @Override
    public CountryDTO findCountryByName(String name) {
        Optional<CountryEntity> countryEntity = countryRepository.findByName(name);
        return mapToCountryDTO(countryEntity);
    }

    @Override
    public List<CountryDTO> findAll() {
        List<CountryEntity> countryEntities = countryRepository.findAll();
        countryEntities.sort(Comparator.comparing(o -> o.getName()));
        List<CountryDTO> countryDTOList = countryEntities.stream().map(a->new CountryDTO(a.getId(),a.getName())).collect(Collectors.toList());

        return countryDTOList;
    }


}
