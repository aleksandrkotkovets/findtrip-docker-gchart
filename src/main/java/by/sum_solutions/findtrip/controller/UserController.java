package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.exception.EditParameterIsExistException;
import by.sum_solutions.findtrip.exception.UserNotFoundException;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import by.sum_solutions.findtrip.exception.RegistrationParameterIsExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    // show registration form for administrator
    @GetMapping(value = "/adminRegistration")
    public String showAdminRegistrationForm(Model model) {
        LOGGER.info("Show registration form by admin");
        model.addAttribute("user", new UserDTO());
        return "adminRegistration";
    }

    // create userEntity with ROLE_ADMIN
    @PostMapping(path = "/adminRegistration")
    public String createAdmin(@Valid @ModelAttribute("user") UserDTO admin, BindingResult result, Model model) {

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
        return "redirect:/users";
    }

  /*  @GetMapping(value="/{id}")
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

    }*/

    /*@ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
        LOGGER.error("Requested URL="+request.getRequestURL());
        LOGGER.error("Exception Raised="+ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("error");
        return modelAndView;
    }*/


    @GetMapping
    public String getAllUsersByRole(@RequestParam(value = "role", defaultValue = "ROLE_ADMIN") Role role, Model model) {
        List<UserDTO> users = userService.getUsersByRole(role);
        model.addAttribute("users", users.size() == 0 ? null : users);
        return "showUsers";
    }

    //    @DeleteMapping(path = "/delete/{id}")
    @GetMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping(path = "/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) throws UserNotFoundException {
        UserDTO userDTO = userService.findUserById(id);
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
        } else {
            throw new UserNotFoundException("User with id=" + id + " not found");
        }
        return "editUser";
    }

    @PostMapping(path = "/edit")
    public String editUser(@ModelAttribute("user") UserDTO user, BindingResult result, Model model) {

        System.out.println(user);
        if (userService.getUserByCriteria(user.getEmail(), null, null) != null
                && userService.getUserByCriteria(user.getEmail(), null, null).getId() != user.getId()) {
            throw new EditParameterIsExistException("User with this email already exist");
        }

        if (userService.getUserByCriteria(null, user.getLogin(), null) != null
                && userService.getUserByCriteria(null, user.getLogin(), null).getId() != user.getId()) {
            throw new EditParameterIsExistException("This login is exist");
        }

        if (userService.getUserByCriteria(null, null, user.getPhoneNumber()) != null
                && userService.getUserByCriteria(null, null, user.getPhoneNumber()).getId() != user.getId()) {
            throw new EditParameterIsExistException("This phone number already exist");
        }

        userService.update(user);
        return "redirect:/users";
    }

}
