package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.AdminDTO;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.repository.entity.UserEntity;
import by.sum_solutions.findtrip.service.UserService;
import by.sum_solutions.findtrip.service.impl.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    public String registerUserAccount(@ModelAttribute("admin") @Valid AdminDTO adminDTO,
            BindingResult result, WebRequest request, Errors errors) {
        UserEntity registered = new UserEntity();
        if (!result.hasErrors()) {
            registered = createUserAccount(adminDTO, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        return "adminRegistration";
    }

    private UserEntity createUserAccount(AdminDTO accountDto, BindingResult result) {
        UserEntity registered = null;
        UserEntity newUser = null;
        newUser.setLogin(accountDto.getLogin());
        newUser.setPassword(accountDto.getPassword());
        newUser.setPatronymic(accountDto.getPatronymic());
        newUser.setLastName(accountDto.getLastName());
        newUser.setFirstName(accountDto.getFirstName());
        newUser.setPhoneNumber(accountDto.getPhoneNumber());
        newUser.setRole(Role.ROLE_ADMIN);

        try {
            registered = userService.save(newUser);
        } catch (Exception e) {
            return null;
        }
        return registered;
    }

    @GetMapping(value = "/adminRegistration")
    public String showAdminRegistrationForm(Model model){
        AdminDTO   adminDTO = new AdminDTO();
        model.addAttribute("admin", adminDTO);
        return "adminRegistration";
    }
}
