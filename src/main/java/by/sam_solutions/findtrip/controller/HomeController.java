package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.*;
import by.sam_solutions.findtrip.exception.RegistrationParameterIsExistException;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    CountryService countryService;

    @Autowired
    CityService cityService;

    @Autowired
    FlightService flightService;

    @ModelAttribute("countries")
    public List<CountryDTO> getCountries(){
        return countryService.findAll(Sort.by("name"));
    }

    @GetMapping(value = "/home")
    public String getMainAdminView(Model model, @AuthenticationPrincipal CustomUserDetail currentUser) {
        Long idUser = userService.getUserByCriteria(null, currentUser.getUsername(), null);
        model.addAttribute("id", idUser);
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
        modelAndView.addObject("picker1",dateDeparture);
        modelAndView.addObject("city_from",cityService.findOne(idCityDeparture));
        modelAndView.addObject("city_to",cityService.findOne(idCityArrival));
        modelAndView.setViewName("flight/showFlights");
        List<FlightDTO> flightDTOList = null;
        try {
            flightDTOList = flightService.findFlightsByCriteria(idCityDeparture, idCityArrival, dateDeparture);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("flights", flightDTOList.size()==0? null: flightDTOList);
        return modelAndView;
    }

    @GetMapping("/findFlights")
    public String getFlightByCriteria(Model model) {
        model.addAttribute("flights",  null);
        return "flight/showFlights";
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
        if (userService.getUserByCriteria(client.getEmail(), null, null) != null) {
            throw new RegistrationParameterIsExistException("User_with_this_email_already_exist", client);
        }

        if (userService.getUserByCriteria(null, client.getLogin(), null) != null) {
            throw new RegistrationParameterIsExistException("This_login_is_exist", client);
        }

        if (userService.getUserByCriteria(null, null, client.getPhoneNumber()) != null) {
            throw new RegistrationParameterIsExistException("This_phone_number_already_exist", client);
        }
        userService.save(client, "ROLE_CLIENT");

        return "redirect:/";
    }


    @GetMapping("/403")
    public String error403() {
        return "403";
    }

}
