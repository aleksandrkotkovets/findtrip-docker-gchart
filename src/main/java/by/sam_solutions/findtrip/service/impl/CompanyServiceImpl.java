package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.controller.dto.CompanyDTO;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.controller.dto.PlaneDTO;
import by.sam_solutions.findtrip.exception.UserNotFoundException;
import by.sam_solutions.findtrip.repository.CompanyRepository;
import by.sam_solutions.findtrip.repository.entity.CompanyEntity;
import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
import by.sam_solutions.findtrip.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;


    @Transactional
    @Override
    public Page<CompanyEntity> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }


    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CompanyDTO findOne(Long id) {
        Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
        if (companyEntity.isPresent()) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setId(companyEntity.get().getId());
            companyDTO.setName(companyEntity.get().getName());
            companyDTO.setRating(companyEntity.get().getRating());

            List<PlaneDTO> planeDTOList = companyEntity
                    .get()
                        .getPlanes()
                    .stream()
                        .map(p->new PlaneDTO(p.getId(),p.getName(),p.getSideNumber(),
                                new CompanyDTO(p.getCompany().getId(), p.getCompany().getName(), p.getCompany().getRating())))
                    .collect(Collectors.toList());

            planeDTOList.sort(Comparator.comparing(o -> o.getName()));
            companyDTO.setPlaneDTOList(planeDTOList);
            return companyDTO;
        }
        return null;
    }


    @Override
    public Long getCompanyIdByName(String name) {
        return companyRepository.getCompanyIdByName(name);
    }

    @Transactional
    @Override
    public void save(CompanyDTO companyDTO) {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(companyDTO.getName());
        companyEntity.setRating(companyDTO.getRating());
        companyRepository.save(companyEntity);

    }

    @Transactional
    @Override
    public void update(CompanyDTO companyDTO){
        Optional<CompanyEntity> editCompanyEntity = companyRepository.findById(companyDTO.getId());
        if( editCompanyEntity.isPresent()){
            editCompanyEntity.get().setName(companyDTO.getName());
            editCompanyEntity.get().setRating(companyDTO.getRating());
            companyRepository.save(editCompanyEntity.get());
        }else {
            throw new UserNotFoundException("Company with id="+companyDTO.getId()+" not found");
        }
    }

    @Override
    public CompanyDTO findCompanyByName(String company) {
        CompanyEntity companyEntity = companyRepository.findByName(company);
        CompanyDTO companyDTO = new CompanyDTO();
        if(companyEntity!=null){
            companyDTO.setId(companyEntity.getId());
            companyDTO.setName(companyEntity.getName());
        }else {
            throw new EntityNotFoundException("Company with name="+company+" not found!");
        }
        return companyDTO;
    }


}
