package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.controller.dto.CityDTO;
import by.sam_solutions.findtrip.controller.dto.CompanyDTO;
import by.sam_solutions.findtrip.controller.dto.PlaneDTO;
import by.sam_solutions.findtrip.exception.EditCityParametersExistException;
import by.sam_solutions.findtrip.service.CompanyService;
import by.sam_solutions.findtrip.service.PlaneService;
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

    @Autowired
    PlaneService planeService;

    @Autowired
    CompanyService companyService;


    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String getAddOrEditCityView(
            Model model,
            @RequestParam(name = "company", required = false) String company,
            @PathVariable(value = "id") Optional<Long> id) throws EntityNotFoundException {

        if (id.isPresent()) {
            PlaneDTO planeDTO = planeService.findOne(id.get());
            if (planeDTO != null) {
                model.addAttribute("company", planeDTO.getCompanyDTO());
                model.addAttribute("plane", planeDTO);
            } else {
                throw new EntityNotFoundException("Plane with id=" + id + " not found");
            }

            return "plane/editPlane";
        } else {
            PlaneDTO planeDTO = new PlaneDTO();
            CompanyDTO companyDTO = companyService.findCompanyByName(company);
            model.addAttribute("company", companyDTO);
            model.addAttribute("plane", planeDTO);
            return "plane/editPlane";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable(value = "id") Long id) {
        planeService.deleteById(id);
        return "redirect:/companies";
    }


    @PostMapping(path = "/edit")
    public String addOrEditCountry(@Valid @ModelAttribute("plane") PlaneDTO planeDTO,
                                   @RequestParam("companyName") String companyName,
                                   @RequestParam("companyId") Long companyId,
                                   BindingResult result, Model model) {

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

        String redirect = "redirect:/companies/" + companyId + "/planes";
        return redirect;
    }


}
