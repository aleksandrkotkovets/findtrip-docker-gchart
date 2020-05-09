package com.sam_solutions.findtrip.service;

import com.sam_solutions.findtrip.controller.dto.CompanyDTO;
import com.sam_solutions.findtrip.controller.dto.PlaneDTO;
import com.sam_solutions.findtrip.repository.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CompanyService {


    Page<CompanyEntity> findAll(Pageable pageable);

    void deleteById(Long id);
    

    CompanyDTO findOne(Long aLong);

    Long getCompanyIdByName(String name);


    @Transactional
    void save(CompanyDTO companyDTO);

    @Transactional
    void update(CompanyDTO companyDTO);

    CompanyDTO findCompanyByName(String company);

    List<CompanyDTO> findAll();

    Page<CompanyEntity> findAllByCriteria(PageRequest pageRequest, String name1);

    List<PlaneDTO> checkPlaneDTOList(List<PlaneDTO> planeDTOList);
}
