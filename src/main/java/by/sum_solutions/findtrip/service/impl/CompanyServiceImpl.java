package by.sum_solutions.findtrip.service.impl;

import by.sum_solutions.findtrip.repository.ICompanyRepository;
import by.sum_solutions.findtrip.repository.entity.CompanyEntity;
import by.sum_solutions.findtrip.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    ICompanyRepository companyRepository;

    @Override
    public CompanyEntity add(CompanyEntity company) {
        CompanyEntity savedCompany = companyRepository.save(company);

        return savedCompany;
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public void delete(CompanyEntity company) {
        companyRepository.delete(company);
    }

    @Override
    public CompanyEntity getByName(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public CompanyEntity update(CompanyEntity company) {
        return companyRepository.saveAndFlush(company);
    }

    @Override
    public List<CompanyEntity> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public List<CompanyEntity> getCompaniesByRating(String rating) {
        return companyRepository.getCompanyEntitiesByRating(rating);
    }

    @Override
    public List<CompanyEntity> getCompanyWithMaxRating() {
        return companyRepository.getCompanyEntitiesByRating_FiveStarsLike();
    }
}
