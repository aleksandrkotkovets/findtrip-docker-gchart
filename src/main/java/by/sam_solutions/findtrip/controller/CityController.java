package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.exception.EditCityParametersExistException;
import by.sam_solutions.findtrip.service.CityService;
import by.sam_solutions.findtrip.service.CountryService;
import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.controller.dto.CityDTO;
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
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String getAddOrEditCityView(
            Model model,
            @RequestParam(name = "country", required = false) String country,
            @PathVariable(value = "id") Optional<Long> id ) throws EntityNotFoundException {

        if (id.isPresent()) {
            CityDTO cityDTO = cityService.findOne(id.get());
            if (cityDTO != null) {
                model.addAttribute("countryName", cityDTO.getCountryDTO().getName());
                model.addAttribute("city", cityDTO);
            } else {
                throw new EntityNotFoundException("City with id=" + id + " not found");
            }

            return "city/editCity";
        } else {
            model.addAttribute("countryName", country);
            model.addAttribute("city", new CityDTO());
            return "city/editCity";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable(value = "id") Long id){
        Long idCountry = cityService.getCountryIdByCityId(id);
        cityService.delete(id);
        return "redirect:/country/"+idCountry+"/cities";
    }


    @PostMapping(path = "/edit")
    public String addOrEditCountry(@Valid @ModelAttribute("city") CityDTO cityDTO,
                                   @RequestParam(name = "countryName") String countryName,
                                   BindingResult result,
                                   Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("city",cityDTO);
            model.addAttribute("countryName",cityDTO);
            model.addAttribute("apiError",apiError);
            return "city/editCity";
        }

        if (cityDTO.getId() != null) {

            if (cityService.getCityIdByName(cityDTO.getName()) != null
                    && cityService.getCityIdByName(cityDTO.getName()) != cityDTO.getId()) {
                throw new EditCityParametersExistException("City_with_this_name_already_exist", cityDTO, countryName);
            }
            cityService.saveOrUpdate(cityDTO, null);
        } else {
            if (cityService.getCityIdByName(cityDTO.getName()) != null) {
                throw new EditCityParametersExistException("City_with_this_name_already_exist", cityDTO, countryName);
            }
            cityService.saveOrUpdate(cityDTO, countryName);
        }
        Long idCountry = countryService.getCountryIdByName(countryName);
        return "redirect:/country/"+idCountry+"/cities";
    }


}
