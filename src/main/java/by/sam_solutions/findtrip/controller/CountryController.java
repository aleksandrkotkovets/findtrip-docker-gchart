package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.exception.EditCountryParametersExistException;
import by.sam_solutions.findtrip.repository.entity.Rating;
import by.sam_solutions.findtrip.service.CountryService;
import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.repository.entity.CountryEntity;
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
import java.util.Optional;

@Controller
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping()
    public String showPage(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page) {

        Page<CountryEntity> countryEntities = countryService.findAll(PageRequest.of(page, 8, Sort.by("name").ascending()));
        model.addAttribute("countries", countryEntities.getTotalElements() == 0? null : countryEntities  );
        model.addAttribute("currentPage", page);
        return "country/countries";
    }


    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String getAddOrEditCountryView(
            Model model,
            @PathVariable(value = "id") Optional<Long> id
    ) throws EntityNotFoundException {

        if (id.isPresent()) {
            CountryDTO countryDTO = countryService.findOne(id.get());
            if (countryDTO != null) {
                model.addAttribute("country", countryDTO);
            } else {
                throw new EntityNotFoundException("Country with id=" + id + " not found");
            }
            return "country/editCountry";
        } else {
            model.addAttribute("country", new CountryDTO());
            return "country/editCountry";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable(value = "id") Long id){
        countryService.delete(id);
        return "redirect:/country";
    }


    @PostMapping(path = "/edit")
    public String addOrEditCountry(@Valid @ModelAttribute("country") CountryDTO country,
                                BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("country",country);
            model.addAttribute("apiError",apiError);
            return "country/editCountry";
        }

        if (country.getId() != null) {

            if (countryService.getCountryIdByName(country.getName()) != null
                    && countryService.getCountryIdByName(country.getName()) != country.getId()) {
                throw new EditCountryParametersExistException("Country_with_this_name_already_exist", country);
            }
            countryService.saveOrUpdate(country);
        } else {
            if (countryService.getCountryIdByName(country.getName()) != null) {
                throw new EditCountryParametersExistException("Country_with_this_name_already_exist", country);
            }
            countryService.saveOrUpdate(country);
        }
        return "redirect:/country";
    }


   @GetMapping("/{id}/cities")
    public String getCitiesByCountryId(@PathVariable(value = "id") Long id,
                                       Model model){
       CountryDTO countryDTO = countryService.findOne(id);
       model.addAttribute("country", countryDTO);
       model.addAttribute("cities", countryDTO.getCityDTOList().size()==0 || countryDTO.getCityDTOList()==null ? null: countryDTO.getCityDTOList());
       return "city/cities";
   }
}
