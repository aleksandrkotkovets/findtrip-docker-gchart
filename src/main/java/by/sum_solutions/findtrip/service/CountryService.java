package by.sum_solutions.findtrip.service;

import by.sum_solutions.findtrip.controller.dto.CountryDTO;
import by.sum_solutions.findtrip.repository.entity.CountryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CountryService {

    Page<CountryEntity> findAll(Pageable pageable);

    void delete(Long id);

    CountryDTO findOne(Long id);

    Long getCountryIdByName(String name);

    void saveOrUpdate(CountryDTO countryDTO);

    CountryDTO findCountryByName(String name);

}
