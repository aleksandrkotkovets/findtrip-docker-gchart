package by.sum_solutions.findtrip.service;

import by.sum_solutions.findtrip.repository.entity.CompanyEntity;

import java.util.List;


public interface CompanyService {

    CompanyEntity add(CompanyEntity company);
    void deleteById(Long id);
    void delete(CompanyEntity company);
    CompanyEntity getByName(String name);
    CompanyEntity update(CompanyEntity company);
    List<CompanyEntity> getAll();
    List<CompanyEntity> getCompaniesByRating(String rating);

}
