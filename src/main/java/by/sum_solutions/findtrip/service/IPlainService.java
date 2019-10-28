package by.sum_solutions.findtrip.service;

import by.sum_solutions.findtrip.repository.entity.CompanyEntity;
import by.sum_solutions.findtrip.repository.entity.PlainEntity;

import java.util.List;

public interface IPlainService {

    PlainEntity add(PlainEntity plain);

    PlainEntity update(PlainEntity plain);

    List<PlainEntity> getAll();

    List<PlainEntity> findAllByCompanyId(Long companyId);

    PlainEntity findById();

    PlainEntity findByName(String name);

    void delete(PlainEntity plainEntity);

    void deleteById(Long id);

    void deleteAllByCompanyId(Long companyId);

    void updateByName(String name,PlainEntity newPlain);

    void updateById(Long id,PlainEntity newPlain);

}
