package by.sum_solutions.findtrip.service.impl;


import by.sum_solutions.findtrip.repository.CompanyRepository;
import by.sum_solutions.findtrip.repository.PlainRepository;
import by.sum_solutions.findtrip.repository.entity.PlainEntity;
import by.sum_solutions.findtrip.service.PlainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlainServiceImpl  implements PlainService {

    @Autowired
    PlainRepository plainRepository;

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public PlainEntity add(PlainEntity plain) {
        PlainEntity savedPlain = plainRepository.save(plain);
        return savedPlain;
    }

    @Override
    public PlainEntity update(PlainEntity plain) {
        return null;
    }

    @Override
    public List<PlainEntity> getAll() {
        return null;
    }

    @Override
    public List<PlainEntity> findAllByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public PlainEntity findById() {
        return null;
    }

    @Override
    public PlainEntity findByName(String name) {
        return null;
    }

    @Override
    public void delete(PlainEntity plainEntity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAllByCompanyId(Long companyId) {

    }

    @Override
    public void updateByName(String name, PlainEntity newPlain) {

    }

    @Override
    public void updateById(Long id, PlainEntity newPlain) {

    }
}
