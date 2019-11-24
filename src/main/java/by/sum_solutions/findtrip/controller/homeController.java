package by.sum_solutions.findtrip.controller;

import by.sum_solutions.findtrip.controller.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class homeController {

    @GetMapping(value = "/homeAdmin")
    public String getMainAdminView() {
        return "homeAdmin";
    }

    @GetMapping(value = "/homeWorker")
    public String getMainWorkerView() {
        return "homeWorker";
    }

    @GetMapping(value = "/homeClient")
    public String getMainClientView() {
        return "homeClient";
    }

    @GetMapping()
    public String getMainPage(){
        return "findTrip";
    }


    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value ="/sign-up")
    public String getSignUpView(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "signUp";
    }
    @GetMapping("/403")
    public String error403() {
        return "403";
    }

}
