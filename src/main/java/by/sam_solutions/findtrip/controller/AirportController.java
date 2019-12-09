package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.AirportDTO;
import by.sam_solutions.findtrip.controller.dto.CityDTO;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.service.CityService;
import by.sam_solutions.findtrip.service.CountryService;
import by.sam_solutions.findtrip.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    AirportService airportService;

    @Autowired
    CountryService countryService;

    @Autowired
    CityService cityService;

    @GetMapping
    public String getShowAirportsView(Model model){
        List<AirportDTO> airportDTOs  = airportService.findAll();
        model.addAttribute("airports", airportDTOs.size() == 0 ? null: airportDTOs);
        return "airport/showAirports";
    }

    @GetMapping("/edit/{id}")
    public String getEditAirportView(@PathVariable Long id, Model model){
        AirportDTO airportDTO = airportService.findById(id);
        model.addAttribute("airport", airportDTO);
        return "airport/editAirport";
    }

    @PostMapping("/edit")
    public String editAirport(@RequestParam Long id,
                              @RequestParam String name,
                              @RequestParam String code){

        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setId(id);
        airportDTO.setName(name);
        airportDTO.setCode(code);

        airportService.updateAirport(airportDTO);
        return "redirect:/airports";
    }

    @GetMapping("/delete/{id}")
    public String deleteAirport(@PathVariable Long id){
        airportService.delete(id);
        return "redirect:/airports";
    }

    @GetMapping("/add")
    public String getAddAirportView(Model model){
        List<CountryDTO> countryDTOS = countryService.findAll();
        model.addAttribute("countries", countryDTOS);
        model.addAttribute("cities", new ArrayList<CityDTO>());
        return "airport/addAirport";
    }

    @PostMapping(value = "/add/getCities")
    @ResponseBody
    public List<CityDTO> getCities(@RequestBody CountryDTO obj, Model model) {
        List<CityDTO> list = cityService.getCityListByCountry(obj.getId());
        return list;
    }

    @PostMapping("/add")
    public String addAirport(@RequestParam String name,
                             @RequestParam String code,
                             @RequestParam Long idCity){
        AirportDTO airportDTO = new AirportDTO(name, code, idCity);
        airportService.save(airportDTO);
        return "redirect:/airports";
    }


}
