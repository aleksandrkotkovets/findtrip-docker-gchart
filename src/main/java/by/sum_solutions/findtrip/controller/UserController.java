package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.repository.entity.UserEntity;
import by.sum_solutions.findtrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/adminRegistration")
    public String showAdminRegistrationForm(Model model){
        model.addAttribute("admin", new UserDTO());
        return "adminRegistration";
    }

    @PostMapping(path = "/adminRegistration")
    public String createOrUpdateAdmin(@ModelAttribute @Valid UserDTO admin, BindingResult errors, Model model)
    {
        if(errors.hasErrors()){
            return "adminRegistration";
        }
        admin.setRole(Role.ROLE_ADMIN);
        userService.save(admin);
        return "redirect:/users/adminRegistration";
    }


}
