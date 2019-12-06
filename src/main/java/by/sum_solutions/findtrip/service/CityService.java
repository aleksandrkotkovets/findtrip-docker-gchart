package by.sum_solutions.findtrip.service;

import by.sum_solutions.findtrip.controller.dto.CityDTO;

public interface CityService {
    CityDTO findOne(Long id);

    void delete(Long id);

    Long getCityIdByName(String name);

    void saveOrUpdate(CityDTO cityDTO, String cuntryName);
}
