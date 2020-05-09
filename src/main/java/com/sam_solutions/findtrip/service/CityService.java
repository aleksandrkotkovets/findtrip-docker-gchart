package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.CityDTO;

import java.util.List;

public interface CityService {
    CityDTO findOne(Long id);

    void delete(Long id);

    Long getCityIdByName(String name);

    void saveOrUpdate(CityDTO cityDTO, String cuntryName);

    List<CityDTO> getCityListByCountry(Long id);

    Long getCountryIdByCityId(Long id);
}
