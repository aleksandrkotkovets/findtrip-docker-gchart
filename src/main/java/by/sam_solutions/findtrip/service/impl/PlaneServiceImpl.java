package by.sam_solutions.findtrip.service.impl;


import by.sam_solutions.findtrip.controller.dto.CompanyDTO;
import by.sam_solutions.findtrip.controller.dto.PlaneDTO;
import by.sam_solutions.findtrip.exception.EditPlaneParametersExistException;
import by.sam_solutions.findtrip.repository.CompanyRepository;
import by.sam_solutions.findtrip.repository.PlaneRepository;
import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
import by.sam_solutions.findtrip.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneServiceImpl  implements PlaneService {

    private PlaneRepository planeRepository;
    private CompanyRepository companyRepository;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository, CompanyRepository companyRepository) {
        this.planeRepository = planeRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    @Override
    public PlaneEntity add(PlaneEntity plain) {
        PlaneEntity savedPlain = planeRepository.save(plain);
        return savedPlain;
    }


    @Override
    public List<PlaneEntity> getAll() {
        return null;
    }


    @Override
    public PlaneEntity findById() {
        return null;
    }

    @Override
    public PlaneDTO findOne(Long id) {
        Optional<PlaneEntity> planeEntity =  planeRepository.findById(id);

        if(planeEntity.isPresent()){
            PlaneDTO planeDTO = new PlaneDTO();
            planeDTO.setId(planeEntity.get().getId());
            planeDTO.setName(planeEntity.get().getName());
            planeDTO.setSideNumber(planeEntity.get().getSideNumber());

            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setName(planeEntity.get().getCompany().getName());
            companyDTO.setId(planeEntity.get().getCompany().getId());
            planeDTO.setCompanyDTO(companyDTO);

            return planeDTO;
        }else {
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        planeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void saveOrUpdate(PlaneDTO planeDTO, Long companyId, String  companyName) {

        Long idExistPlane = this.getPlaneIdBySideNumber(planeDTO.getSideNumber());
        String firstLetterСompName = companyName.substring(0,1);
        String firstLetterSideNumber = planeDTO.getSideNumber().substring(0,1);

        if(!firstLetterСompName.equals(firstLetterSideNumber)) {
            throw new EditPlaneParametersExistException(
                    "The_first_letter_of_the_tail_number_must_match_the_name_of_the_company",
                    planeDTO, companyName, companyId);
        }

        if (planeDTO.getId() != null) {

            PlaneEntity planeEntityEdit = planeRepository.findById(planeDTO.getId()).get();
            if(planeEntityEdit != null) {
                if (idExistPlane != null && idExistPlane != planeDTO.getId()) {
                    throw new EditPlaneParametersExistException(
                            "Plane_with_this_side_number_already_exist", planeDTO, companyName, companyId);
                }
                planeEntityEdit.setName(planeDTO.getName());
                planeEntityEdit.setSideNumber(planeDTO.getSideNumber());
                planeRepository.save(planeEntityEdit);
            }else {
                throw new EntityNotFoundException("Plane with id="+planeDTO.getId()+" not found");
            }

        } else {
            if (idExistPlane != null && idExistPlane != planeDTO.getId()) {
                throw new EditPlaneParametersExistException(
                        "Plane_with_this_side_number_already_exist",planeDTO,companyName,companyId );
            }
            PlaneEntity planeEntity = new PlaneEntity(
                    planeDTO.getName(),planeDTO.getSideNumber(), companyRepository.findByName(companyName));
            planeRepository.save(planeEntity);
        }
    }

    @Override
    public Long getCompanyIdByPlaneId(Long id) {
       return planeRepository.getCompanyIdByPlaneId(id);
    }

    @Override
    public Long getPlaneIdBySideNumber(String sideNumber) {
       return planeRepository.findIdBySideNumber(sideNumber);
    }
}
