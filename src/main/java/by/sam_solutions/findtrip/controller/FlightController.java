package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.*;
import by.sam_solutions.findtrip.service.CityService;
import by.sam_solutions.findtrip.service.CompanyService;
import by.sam_solutions.findtrip.service.CountryService;
import by.sam_solutions.findtrip.service.FlightService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    CountryService countryService;

    @Autowired
    CityService cityService;

    @Autowired
    CompanyService companyService;

    @ModelAttribute("countries")
    public List<CountryDTO> getCountries() {
        return countryService.findAll();
    }


    @ModelAttribute("companies")
    public List<CompanyDTO> getCompanies() {
        return companyService.findAll();
    }


    @GetMapping("{id}")
    public String getEditFlightView(@PathVariable(value = "id") Long id) {
        return "flight/addFlight";
    }

    @GetMapping("/create")
    public String getAddFlightView(Model model) {
        model.addAttribute("flight", new FlightDTO());
        return "flight/addFlight";
    }

    @GetMapping(value = "/countries/{id}")
    @ResponseBody
    public List<CityDTO> getCities(@PathVariable Long id) {
        List<CityDTO> list = cityService.getCityListByCountry(id);
        return list;
    }

    @GetMapping(value = "/cities/{id}")
    @ResponseBody
    public List<AirportDTO> getAirports(@PathVariable Long id) {
        CityDTO cityDTO = cityService.findOne(id);
        return cityDTO.getAirportDTOList();
    }

    @GetMapping(value = "/companies/{id}")
    @ResponseBody
    public List<PlaneDTO> getPlanes(@PathVariable Long id) {
        CompanyDTO companyDTO = companyService.findOne(id);
        return companyDTO.getPlaneDTOList();
    }

    @PostMapping()
    public String addFlight(@RequestBody FlightCreateUpdateDTO flightDTO) {
        flightService.addFlight(flightDTO);
        return "flight/addFlight";
    }



}
