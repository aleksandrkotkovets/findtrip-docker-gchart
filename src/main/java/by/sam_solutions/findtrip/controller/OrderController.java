package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("/checkout")
    @ResponseBody
    public OrderCreateUpdateDTO addTicket(@RequestBody OrderCreateUpdateDTO orderDTO, @AuthenticationPrincipal CustomUserDetail currentUser) {
        orderDTO.setIdClient(currentUser.getId());
        orderService.add(orderDTO);
        return orderDTO;
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("/client")
    public String getOrdersByUser(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        model.addAttribute("orders", orderService.getOrdersByUserId(currUser.getId()));
        return "withrole/client/myFlights";
    }

    @GetMapping("/return/{id}")
    public String getOrderById(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/returnTickets";
    }

    @PostMapping("/return")
    @ResponseBody
    public OrderCreateUpdateDTO deleteTicketsOnFlightByUser(@RequestBody OrderCreateUpdateDTO order, @AuthenticationPrincipal CustomUserDetail currUser) {
        order.setIdClient(currUser.getId());
        return orderService.deleteTicketsOnFlightByUSer(order);
    }

}
