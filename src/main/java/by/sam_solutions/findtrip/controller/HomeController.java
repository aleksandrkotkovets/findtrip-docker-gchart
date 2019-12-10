package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.exception.RegistrationParameterIsExistException;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.RoleService;
import by.sam_solutions.findtrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping(value = "/home")
    public String getMainAdminView(Model model,@AuthenticationPrincipal CustomUserDetail currentUser) {
        Long idUser =  userService.getUserByCriteria(null,currentUser.getUsername(), null);
        model.addAttribute("id", idUser);
        return "home/home";
    }

    @GetMapping()
    public String getMainPage(){
        return "home/home";
    }


    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping(value ="/registration")
    public String getSignUpView(Model model){
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
            model.addAttribute("user",client);
            model.addAttribute("apiError",apiError);
            return "registration";
        }
        if (userService.getUserByCriteria(client.getEmail(), null, null) != null) {
            throw new RegistrationParameterIsExistException("User_with_this_email_already_exist", client);
        }

        if (userService.getUserByCriteria(null, client.getLogin(), null) != null) {
            throw new RegistrationParameterIsExistException("This_login_is_exist", client);
        }

        if (userService.getUserByCriteria(null, null, client.getPhoneNumber()) != null) {
            throw new RegistrationParameterIsExistException("This_phone_number_already_exist",client);
        }
        userService.save(client, "ROLE_CLIENT");

        return "redirect:/";
    }


    @GetMapping("/403")
    public String error403() {
        return "403";
    }

}
