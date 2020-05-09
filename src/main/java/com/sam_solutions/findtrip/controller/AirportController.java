package com.sam_solutions.findtrip.controller;

import com.sam_solutions.findtrip.controller.dto.AirportDTO;
import com.sam_solutions.findtrip.controller.dto.CityDTO;
import com.sam_solutions.findtrip.service.AirportService;
import com.sam_solutions.findtrip.service.CityService;
import com.sam_solutions.findtrip.service.CountryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/airports")
public class AirportController {
    private final static Logger LOGGER = LogManager.getLogger();

    private AirportService airportService;
    private CountryService countryService;
    private CityService cityService;

    @Autowired
    public AirportController(AirportService airportService, CountryService countryService, CityService cityService) {
        this.airportService = airportService;
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @GetMapping
    public String getShowAirportsView(Model model) {
        LOGGER.info("Get showAirportsView: airport/showAirports.html");
        List<AirportDTO> airportDTOs = airportService.findAll();
        model.addAttribute("airports", airportDTOs.size() == 0 ? null : airportDTOs);
        return "airport/showAirports";
    }

    @GetMapping("/edit/{id}")
    public String getEditAirportView(@PathVariable Long id, Model model) {
        LOGGER.info("Get editAirportView: airport/editAirport");
        AirportDTO airportDTO = airportService.findById(id);
        model.addAttribute("airport", airportDTO);
        return "airport/editAirport";
    }

    @PostMapping("/edit")
    public String editAirport(@RequestParam Long id,
                              @RequestParam String name,
                              @RequestParam String code) {
        LOGGER.info("Update airport where id: " + id + ", new name: " + name + ", new code: " + code);
        airportService.updateAirport(new AirportDTO(id, name, code));
        return "redirect:/airports";
    }

    @GetMapping("/delete/{id}")
    public String deleteAirport(@PathVariable Long id) {
        LOGGER.info("Delete airport where id: " + id);
        airportService.delete(id);
        return "redirect:/airports";
    }

    @GetMapping("/add")
    public String getAddAirportView(Model model) {
        LOGGER.info("Get addAirport: airport/addAirport");
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("cities", new ArrayList<CityDTO>());

        return "airport/addAirport";
    }


    @GetMapping(value = "/countries/{id}")
    @ResponseBody
    public List<CityDTO> getCities(@PathVariable Long id) {
        LOGGER.info("Get cities where country id: " + id);
        return cityService.getCityListByCountry(id);
    }

    @PostMapping("/add")
    public String addAirport(@RequestParam String name,
                             @RequestParam String code,
                             @RequestParam Long idCity) {
        LOGGER.info("Add airport with name: " + name + ", code: " + code + ", idCity: " + idCity);
        airportService.save(new AirportDTO(name, code, idCity));
        return "redirect:/airports";
    }


}
