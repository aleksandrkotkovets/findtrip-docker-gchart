package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.repository.entity.CompanyEntity;
import by.sum_solutions.findtrip.service.ICompanyService;
import by.sum_solutions.findtrip.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @GetMapping()
    public List<CompanyEntity> getAll(){
        return companyService.getAll();
    }
}
