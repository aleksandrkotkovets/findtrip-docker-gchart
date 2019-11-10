package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.exception.RegistrationParameterIsExistException;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


    // show registration form for administrator
    @GetMapping(value = "/adminRegistration")
    public String showAdminRegistrationForm(Model model) {
        model.addAttribute("admin", new UserDTO());
        return "registration";
    }

    // create userEntity with ROLE_ADMIN
    @PostMapping(path = "/adminRegistration")
    @ResponseStatus(HttpStatus.OK)
    public String createAdmin(@Valid @ModelAttribute("admin") UserDTO admin, BindingResult result, Model model)  {

        if (userService.getUserByCriteria(admin.getEmail(), null, null) != null) {
            throw new RegistrationParameterIsExistException("User with this email already exist");
        }

        if (userService.getUserByCriteria(null, admin.getLogin(), null) != null) {
            throw new RegistrationParameterIsExistException("This login is exist");
        }

        if (userService.getUserByCriteria(null, null, admin.getPhoneNumber()) != null) {
            throw new RegistrationParameterIsExistException("This phone number already exist");
        }

        admin.setRole(Role.ROLE_ADMIN);
        userService.save(admin);
        return "redirect:/users/adminRegistration";
    }


}
