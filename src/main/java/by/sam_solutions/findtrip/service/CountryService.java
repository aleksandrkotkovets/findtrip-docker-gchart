package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.repository.entity.CountryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {

    Page<CountryEntity> findAll(Pageable pageable);

    void delete(Long id);

    CountryDTO findOne(Long id);

    Long getCountryIdByName(String name);

    void saveOrUpdate(CountryDTO countryDTO);

    CountryDTO findCountryByName(String name);

    List<CountryDTO> findAll();
}