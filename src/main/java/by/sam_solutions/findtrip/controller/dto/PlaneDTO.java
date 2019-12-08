package by.sam_solutions.findtrip.controller.dto;

import by.sam_solutions.findtrip.repository.entity.CompanyEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlaneDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    private CompanyDTO companyDTO;

    public PlaneDTO(){}

    public PlaneDTO(Long id, String name, CompanyDTO companyDTO) {
        this.id = id;
        this.name = name;
        this.companyDTO = companyDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }
}
