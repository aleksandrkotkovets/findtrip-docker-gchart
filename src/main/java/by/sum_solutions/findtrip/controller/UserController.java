package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.exception.UserNotFoundException;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.service.UserService;
import by.sum_solutions.findtrip.service.impl.UserServiceImpl;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping(value = "/adminRegistration")
    public String showAdminRegistrationForm(Model model) {
        LOGGER.info("Show registration form by admin");
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

    @GetMapping(value="/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) throws Exception{

        if(id==1){
            throw new UserNotFoundException("User Not Found id=",id);
        }else if(id==2){
            throw new SQLException("SQLException, id="+id);
        }else if(id==3){
            throw new IOException("IOException, id="+id);
        }else if(id==10){
           UserDTO userDTO = new UserDTO();
            userDTO.setFirstName("Pankaj");
            userDTO.setRole(Role.ROLE_WORKER);
            userService.save(userDTO);
            model.addAttribute("worker", userDTO);
            return "home";
        }else {
            throw new Exception("Generic Exception, id="+id);
        }

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
        LOGGER.error("Requested URL="+request.getRequestURL());
        LOGGER.error("Exception Raised="+ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("error");
        return modelAndView;
    }


}
