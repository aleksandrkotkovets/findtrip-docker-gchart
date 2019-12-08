package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.controller.dto.CompanyDTO;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.exception.EditCompanyParameterExistException;
import by.sam_solutions.findtrip.exception.EditCountryParametersExistException;
import by.sam_solutions.findtrip.repository.entity.CompanyEntity;
import by.sam_solutions.findtrip.repository.entity.Rating;
import by.sam_solutions.findtrip.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping()
    public String showPage(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page) {

        Page<CompanyEntity> countryEntities = companyService.findAll(PageRequest.of(page, 4, Sort.by("name").ascending()));
        model.addAttribute("companies", countryEntities.getTotalElements() == 0? null : countryEntities  );
        model.addAttribute("currentPage", page);
        return "company/showCompanies";
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String getAddOrEditCompanyView(Model model,@PathVariable(value = "id") Optional<Long> id) throws EntityNotFoundException {

        if (id.isPresent()) {
            CompanyDTO companyDTO = companyService.findOne(id.get());
            if (companyDTO != null) {
                model.addAttribute("company", companyDTO);
                model.addAttribute("ratingTypes", Rating.values());
            } else {
                throw new EntityNotFoundException("Company with id=" + id + " not found");
            }
            return "company/editCompany";
        } else {
            CompanyDTO company = new CompanyDTO();
            model.addAttribute("company", company);
            model.addAttribute("ratingTypes", Rating.values());
            return "company/editCompany";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable(value = "id") Long id){
        companyService.delete(id);
        return "redirect:/companies";
    }

    @PostMapping(path = "/edit")
    public String addOrEditCompany(@Valid @ModelAttribute("company") CompanyDTO company,
                                   BindingResult result,
                                   Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }

            model.addAttribute("company",company);
            model.addAttribute("ratingTypes", Rating.values());
            model.addAttribute("apiError",apiError);
            return "company/editCompany";
        }

        if (company.getId() != null) {

            if (companyService.getCompanyIdByName(company.getName()) != null
                    && companyService.getCompanyIdByName(company.getName()) != company.getId()) {
                throw new EditCompanyParameterExistException("Company_with_this_name_already_exist", company);
            }
            companyService.update(company);
        } else {
            if (companyService.getCompanyIdByName(company.getName()) != null) {
                throw new EditCompanyParameterExistException("Company_with_this_name_already_exist", company);
            }
            companyService.save(company);
        }
        return "redirect:/companies";
    }

    @GetMapping("/{id}/planes")
    public String getCitiesByCountryId(@PathVariable Long id,
                                       Model model){
        CompanyDTO companyDTO = companyService.findOne(id);
        model.addAttribute("company", companyDTO);
        model.addAttribute("planes", companyDTO.getPlaneDTOList().size()==0 || companyDTO.getPlaneDTOList()==null ? null: companyDTO.getPlaneDTOList());
        return "plane/showPlanes";
    }

}
