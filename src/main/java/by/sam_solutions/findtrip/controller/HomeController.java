package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.controller.dto.FlightDTO;
import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.CityService;
import by.sam_solutions.findtrip.service.CountryService;
import by.sam_solutions.findtrip.service.FlightService;
import by.sam_solutions.findtrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {


    private UserService userService;
    private CountryService countryService;
    private CityService cityService;
    private FlightService flightService;

    @Autowired
    public HomeController(UserService userService, CountryService countryService, CityService cityService, FlightService flightService) {
        this.userService = userService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.flightService = flightService;
    }

    @ModelAttribute("countries")
    public List<CountryDTO> getCountries() {
        return countryService.findAll(Sort.by("name").ascending());
    }

    @GetMapping(value = "/home")
    public String getHomeView(Model model, @AuthenticationPrincipal CustomUserDetail currentUser) {
        model.addAttribute("id", currentUser.getId());
        return "home/home";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("countries", countryService.findAll(Sort.by("name").ascending()));
        return "index";
    }

    @PostMapping("/findFlights")
    public ModelAndView getFlightByCriteria(@RequestParam("city_from") Long idCityDeparture,
                                            @RequestParam("city_to") Long idCityArrival,
                                            @RequestParam("picker1") String dateDeparture) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("picker1", dateDeparture);
        modelAndView.addObject("city_from", cityService.findOne(idCityDeparture));
        modelAndView.addObject("city_to", cityService.findOne(idCityArrival));
        modelAndView.setViewName("flight/showFlights");

        List<FlightDTO> flightDTOList = null;
        /** Расскоментировать*/
        /*try {
            flightDTOList = flightService.findFlightsByCriteria(idCityDeparture, idCityArrival, dateDeparture);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        flightDTOList = flightService.findAll();
        modelAndView.addObject("flights", flightDTOList.size() == 0 ? null : flightDTOList);
        return modelAndView;
    }

    @GetMapping("/findFlights")
    public ModelAndView getFlightByCriteria() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("flight/showFlights");
        modelAndView.addObject("flights", null);
        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping(value = "/registration")
    public String getSignUpView(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registrationClient(@Valid @ModelAttribute("user") UserDTO client,
                                     BindingResult result,
                                     Model model) {
        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("user", client);
            model.addAttribute("apiError", apiError);
            return "registration";
        }

        userService.save(client, "ROLE_CLIENT");

        return "redirect:/";
    }


    @GetMapping("/403")
    public String error403() {
        return "statuscode/403";
    }

}
