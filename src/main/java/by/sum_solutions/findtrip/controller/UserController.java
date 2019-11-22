package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.ApiError;
import by.sum_solutions.findtrip.controller.dto.RoleDTO;
import by.sum_solutions.findtrip.controller.dto.UserDTO;
import by.sum_solutions.findtrip.exception.RegistrationParameterIsExistException;
import by.sum_solutions.findtrip.exception.UserNotFoundException;
import by.sum_solutions.findtrip.repository.entity.RoleEntity;
import by.sum_solutions.findtrip.service.RoleService;
import by.sum_solutions.findtrip.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

   /* @GetMapping
    public String getAllUsers(Model model) {
        List<UserDTO> users = new ArrayList<>(); // = userService.getUsersByRole(roleShow);
        List<RoleDTO> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("selectedRole",roles.get(0));
        model.addAttribute("users", users.size() == 0 ? null : users);
        return "showUsers";
    }*/

    @GetMapping()
    public String getAllUsersByRole(@RequestParam(value = "role") String role,  Model model) {
        List<UserDTO> users = userService.getUsersByRole(role);
        List<RoleDTO> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("selectedRole", role);
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
    public String addOrEditUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("user",user);
            model.addAttribute("apiError",apiError);
            return "addEditUser";
        }
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
      //  return "showUsers";
    }
    ///

}
