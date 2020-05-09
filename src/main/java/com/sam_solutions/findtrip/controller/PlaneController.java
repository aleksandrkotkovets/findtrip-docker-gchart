package com.sam_solutions.findtrip.controller;

import com.sam_solutions.findtrip.controller.dto.ApiError;
import com.sam_solutions.findtrip.controller.dto.PlaneDTO;
import com.sam_solutions.findtrip.service.CompanyService;
import com.sam_solutions.findtrip.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/planes")
public class PlaneController {

    private PlaneService planeService;
    private CompanyService companyService;

    @Autowired
    public PlaneController(PlaneService planeService, CompanyService companyService) {
        this.planeService = planeService;
        this.companyService = companyService;
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String getAddOrEditPlaneView(Model model,
                                        @RequestParam(name = "company", required = false) String company,
                                        @PathVariable(value = "id") Optional<Long> id) {

        if (id.isPresent()) {
            PlaneDTO planeDTO = planeService.findOne(id.get());
            if (planeDTO != null) {
                model.addAttribute("companyName", planeDTO.getCompanyDTO().getName());
                model.addAttribute("companyId", planeDTO.getCompanyDTO().getId());
                model.addAttribute("plane", planeDTO);
            } else {
                throw new EntityNotFoundException("Plane with id=" + id + " not found");
            }

            return "plane/editPlane";
        } else {
            model.addAttribute("companyName", company);
            model.addAttribute("plane", new PlaneDTO());
            return "plane/editPlane";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePlane(@PathVariable(value = "id") Long id) {
        Long idCompany = planeService.getCompanyIdByPlaneId(id);
        planeService.deleteById(id);
        return idCompany == null ? "redirect:/companies" : "redirect:/companies/" + idCompany + "/planes";
    }


    @PostMapping(path = "/edit")
    public String addOrEditPlane(@Valid @ModelAttribute("plane") PlaneDTO planeDTO,
                                 @RequestParam("companyName") String companyName,
                                 BindingResult result, Model model) {
        Long companyId = companyService.getCompanyIdByName(companyName);
        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("plane", planeDTO);
            model.addAttribute("companyId", companyId);
            model.addAttribute("companyName", companyName);
            model.addAttribute("apiError", apiError);
            return "plane/editPlane";
        }

        planeService.saveOrUpdate(planeDTO, companyId, companyName);


        return "redirect:/companies/" + companyId + "/planes";
    }


}
