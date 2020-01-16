package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.OrderDTO;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

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
        List<OrderDTO> orderDTOList = orderService.getOrdersByUserId(currUser.getId());
        model.addAttribute("orders", orderDTOList.size() != 0 ? orderDTOList : null);
        return "withrole/client/myFlights";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("/return/{id}")
    public String getOrderById(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/returnTickets";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("/return")
    @ResponseBody
    public OrderCreateUpdateDTO deleteTicketsOnFlightByUser(@RequestBody OrderCreateUpdateDTO order, @AuthenticationPrincipal CustomUserDetail currUser) {
        order.setIdClient(currUser.getId());
        orderService.deleteTicketsOnFlightByUSer(order);
        return order;
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("/{id}/moreTickets")
    public String getTakeMoreTicketsView(@PathVariable Long id,Model model){
        model.addAttribute("order", orderService.findById(id));
        return "order/takeMoreTickets";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("/{id}/moreTickets")
    @ResponseBody
    public OrderCreateUpdateDTO takeMoreTickets(@RequestBody OrderCreateUpdateDTO order, @AuthenticationPrincipal CustomUserDetail currUser){
        order.setIdClient(currUser.getId());
        orderService.takeMoreTickets(order);
        return order;
    }

}
