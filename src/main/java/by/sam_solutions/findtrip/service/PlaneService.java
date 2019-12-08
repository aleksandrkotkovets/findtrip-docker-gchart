package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.repository.entity.PlaneEntity;

import java.util.List;

public interface PlaneService {

    PlaneEntity add(PlaneEntity plain);

    PlaneEntity update(PlaneEntity plain);

    List<PlaneEntity> getAll();

    List<PlaneEntity> findAllByCompanyId(Long companyId);

    PlaneEntity findById();

    PlaneEntity findByName(String name);

    void delete(PlaneEntity plainEntity);

    void deleteById(Long id);

    void deleteAllByCompanyId(Long companyId);

    void updateByName(String name,PlaneEntity newPlain);

    void updateById(Long id,PlaneEntity newPlain);

}
