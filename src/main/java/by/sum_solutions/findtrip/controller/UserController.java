package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.exception.RegistrationParameterIsExistException;
import by.sum_solutions.findtrip.exception.UserNotFoundException;
import by.sum_solutions.findtrip.repository.entity.Role;
import by.sum_solutions.findtrip.service.UserService;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping
    public String getAllUsersByRole(@RequestParam(value = "role", defaultValue = "ROLE_ADMIN") Role role, Model model) {
        List<UserDTO> users = userService.getUsersByRole(role);
        model.addAttribute("users", users.size() == 0 ? null : users);
        return "showUsers";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String getAddOrEditUserView(Model model, @PathVariable("id") Optional<Long> id) throws UserNotFoundException {

        if (id.isPresent()) {
            UserDTO userDTO = userService.findUserById(id.get());
            if (userDTO != null) {
                model.addAttribute("user", userDTO);
            } else {
                throw new UserNotFoundException("User with id=" + id + " not found");
            }
            return "addEditUser";
        } else {
            model.addAttribute("user", new UserDTO());
            return "addEditUser";
        }
    }

    @PostMapping(path = "/edit")
    public String addOrEditUser(@ModelAttribute("user") UserDTO user, BindingResult result, Model model) {
        if (user.getId() != null) {
            if (userService.getUserByCriteria(user.getEmail(), null, null) != null
                    && userService.getUserByCriteria(user.getEmail(), null, null).getId() != user.getId()) {
                throw new RegistrationParameterIsExistException("User with this email already exist", user);
            }

            if (userService.getUserByCriteria(null, user.getLogin(), null) != null
                    && userService.getUserByCriteria(null, user.getLogin(), null).getId() != user.getId()) {
                throw new RegistrationParameterIsExistException("This login is exist", user);
            }

            if (userService.getUserByCriteria(null, null, user.getPhoneNumber()) != null
                    && userService.getUserByCriteria(null, null, user.getPhoneNumber()).getId() != user.getId()) {
                throw new RegistrationParameterIsExistException("This phone number already exist", user);
            }
            userService.update(user);
        } else {

            if (userService.getUserByCriteria(user.getEmail(), null, null) != null) {
                throw new RegistrationParameterIsExistException("User with this email already exist", user);
            }

            if (userService.getUserByCriteria(null, user.getLogin(), null) != null) {
                throw new RegistrationParameterIsExistException("This login is exist", user);
            }

            if (userService.getUserByCriteria(null, null, user.getPhoneNumber()) != null) {
                throw new RegistrationParameterIsExistException("This phone number already exist",user);
            }

            user.setRole(Role.ROLE_ADMIN);
            userService.save(user);
        }
        return "redirect:/users";
    }

    @GetMapping(path = "/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping(path = "login")
    public String login(Model model, Optional<String> login, Optional<String> password){
        if(login.isPresent() && password.isPresent()){
            if(userService.findUserByCriteria(login,password)){
                model.addAttribute("message","success");
                return "login";
            }else{
                throw new UserNotFoundException("Incorrect login or password!");
            }
        }else {
            throw new RegistrationParameterIsExistException("Incorrect login or password!");
        }
        return "showUsers";
    }

}
