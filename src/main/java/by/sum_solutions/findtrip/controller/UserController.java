package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.service.UserService;
import by.sum_solutions.findtrip.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/adminRegistration")
    public String showAdminRegistrationForm(Model model) {
        model.addAttribute("admin", new UserDTO());
        return "registration";
    }

    @PostMapping(path = "/adminRegistration")
    public String createAdmin(@Valid @ModelAttribute("admin") UserDTO admin, BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "registration";
        }

        if (userService.existsUserByLogin(admin.getLogin()) != null
                || userService.existsUserByEmail(admin.getEmail()) != null
                || userService.existsUserByPhoneNumber(admin.getPhoneNumber()) != null) {

            String existLoginError = userService.existsUserByLogin(admin.getLogin());
            model.addAttribute("existLoginError", existLoginError);

            String existEmailError = userService.existsUserByEmail(admin.getEmail());
            model.addAttribute("existEmailError", existEmailError);

            String existPhoneError = userService.existsUserByPhoneNumber(admin.getPhoneNumber());
            model.addAttribute("existPhoneError", existPhoneError);

            return "registration";


        }

        admin.setRole(Role.ROLE_ADMIN);
        userService.save(admin);
        return "redirect:/users/adminRegistration";
    }


}
