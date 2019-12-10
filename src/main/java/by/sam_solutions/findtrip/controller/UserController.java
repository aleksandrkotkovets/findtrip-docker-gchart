package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.ApiError;
import by.sam_solutions.findtrip.controller.dto.UserDTO;
import by.sam_solutions.findtrip.exception.EditUsersParametersExistException;
import by.sam_solutions.findtrip.exception.UserNotFoundException;
import by.sam_solutions.findtrip.service.RoleService;
import by.sam_solutions.findtrip.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping()
    public String getAllUsersByRole(@RequestParam(value = "role") String role,  Model model) {
        List<UserDTO> users = roleService.getUsersByRole(role);
        model.addAttribute("role", role);
        model.addAttribute("users", users.size() == 0 ? null : users);
        return "user/showUsers";
    }

    @GetMapping(path = "/delete")
    public String deleteEmployeeById(Model model,
                                     @RequestParam("id") Long id,
                                     @RequestParam(value = "role") String role ) throws UserNotFoundException {
        userService.deleteUserById(id);
        String redirect = "redirect:/users?&role="+role;
        return redirect;
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String getAddOrEditUserView(
                                       Model model,
                                       @RequestParam(value = "role", required = false, defaultValue = "ROLE_CLIENT") String role,
                                       @PathVariable(value = "id") Optional<Long> id) throws UserNotFoundException {


        if (id.isPresent()) {
            UserDTO userDTO = userService.findUserById(id.get());
            if (userDTO != null) {
                model.addAttribute("user", userDTO);
            } else {
                throw new UserNotFoundException("User with id=" + id + " not found");
            }
            return "user/addEditUser";
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setRole(role);
            model.addAttribute("user", userDTO);
            return "user/addEditUser";
        }
    }

    @PostMapping(path = "/edit")
    public String addOrEditUser(@Valid @ModelAttribute("user") UserDTO user,
                                @RequestParam(value = "role", required = false, defaultValue = "ROLE_CLIENT") String role,
                                BindingResult result,Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("user",user);
            model.addAttribute("apiError",apiError);
            return "user/addEditUser";
        }
        if (user.getId() != null) {
            if (userService.getUserByCriteria(user.getEmail(), null, null) != null
                    && userService.getUserByCriteria(user.getEmail(), null, null) != user.getId()) {
                throw new EditUsersParametersExistException("User_with_this_email_already_exist", user);
            }

            if (userService.getUserByCriteria(null, user.getLogin(), null) != null
                    && userService.getUserByCriteria(null, user.getLogin(), null) != user.getId()) {
                throw new EditUsersParametersExistException("This_login_is_exist", user);
            }

            if (userService.getUserByCriteria(null, null, user.getPhoneNumber()) != null
                    && userService.getUserByCriteria(null, null, user.getPhoneNumber()) != user.getId()) {
                throw new EditUsersParametersExistException("This_phone_number_already_exist", user);
            }
            userService.update(user);
        } else {

            if (userService.getUserByCriteria(user.getEmail(), null, null) != null) {
                throw new EditUsersParametersExistException("User_with_this_email_already_exist", user);
            }

            if (userService.getUserByCriteria(null, user.getLogin(), null) != null) {
                throw new EditUsersParametersExistException("This_login_is_exist", user);
            }

            if (userService.getUserByCriteria(null, null, user.getPhoneNumber()) != null) {
                throw new EditUsersParametersExistException("This_phone_number_already_exist",user);
            }
            userService.save(user, role);

        }
//        String redirect = "redirect:/users?&role="+role;
        return "redirect:/home";
    }


}