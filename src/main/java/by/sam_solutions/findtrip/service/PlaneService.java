package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.PlaneDTO;
import by.sam_solutions.findtrip.repository.entity.PlaneEntity;

import java.util.List;

public interface PlaneService {

    PlaneEntity add(PlaneEntity plain);

    List<PlaneEntity> getAll();

    PlaneEntity findById();

    PlaneDTO findOne(Long id);

    void deleteById(Long id);

    void saveOrUpdate(PlaneDTO planeDTO, Long companyId, String companyName);

    Long getCompanyIdByPlaneId(Long id);

    Long getPlaneIdBySideNumber(String sideNumber);
}
