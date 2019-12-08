package by.sam_solutions.findtrip.service.impl;


import by.sam_solutions.findtrip.repository.CompanyRepository;
import by.sam_solutions.findtrip.repository.PlaneRepository;
import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
import by.sam_solutions.findtrip.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneServiceImpl  implements PlaneService {

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public PlaneEntity add(PlaneEntity plain) {
        PlaneEntity savedPlain = planeRepository.save(plain);
        return savedPlain;
    }

    @Override
    public PlaneEntity update(PlaneEntity plain) {
        return null;
    }

    @Override
    public List<PlaneEntity> getAll() {
        return null;
    }

    @Override
    public List<PlaneEntity> findAllByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public PlaneEntity findById() {
        return null;
    }

    @Override
    public PlaneEntity findByName(String name) {
        return null;
    }

    @Override
    public void delete(PlaneEntity plainEntity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAllByCompanyId(Long companyId) {

    }

    @Override
    public void updateByName(String name, PlaneEntity newPlain) {

    }

    @Override
    public void updateById(Long id, PlaneEntity newPlain) {

    }
}
