package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.config.EmailConfig;
import by.sam_solutions.findtrip.controller.dto.OrderCreateUpdateDTO;
import by.sam_solutions.findtrip.controller.dto.OrderDTO;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.EmailSender;
import by.sam_solutions.findtrip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;
    private EmailSender emailSender;

    @Autowired
    public OrderController(OrderService orderService, EmailSender emailSender) {
        this.orderService = orderService;
        this.emailSender = emailSender;
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("/checkout")
    @ResponseBody
    public OrderDTO addTicket(@RequestBody OrderCreateUpdateDTO orderDTO, @AuthenticationPrincipal CustomUserDetail currentUser) {
        orderDTO.setIdClient(currentUser.getId());
        return  orderService.add(orderDTO);
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
    public OrderDTO takeMoreTickets(@RequestBody OrderCreateUpdateDTO order, @AuthenticationPrincipal CustomUserDetail currUser){
        order.setIdClient(currUser.getId());
        return orderService.takeMoreTickets(order);
    }

    @PostMapping("/sendEmail")
    @ResponseBody
    public OrderDTO sendEmailToClient(@RequestBody OrderDTO orderDTO, @AuthenticationPrincipal CustomUserDetail currUser) {
        emailSender.sendСonfirmPurchaseToEmail(orderDTO);
        return orderDTO;
    }

}
