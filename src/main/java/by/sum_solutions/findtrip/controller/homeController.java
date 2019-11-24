package by.sum_solutions.findtrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


}
