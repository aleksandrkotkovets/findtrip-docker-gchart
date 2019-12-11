package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("{id}")
    public String getEditFlightView(@PathVariable(value = "id") Long id){
        return "flight/addFlight";
    }

    @GetMapping("/create")
    public String getAddFlightView(Model model){
        return "flight/addFlight";
    }

}
