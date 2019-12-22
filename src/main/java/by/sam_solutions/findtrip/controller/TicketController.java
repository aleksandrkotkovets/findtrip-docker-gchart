package by.sam_solutions.findtrip.controller;

import by.sam_solutions.findtrip.controller.dto.TicketCreateUpdateDTO;
import by.sam_solutions.findtrip.security.CustomUserDetail;
import by.sam_solutions.findtrip.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("/buy")
    @ResponseBody
    public TicketCreateUpdateDTO addTicket(@RequestBody TicketCreateUpdateDTO ticketDTO, @AuthenticationPrincipal CustomUserDetail currentUser){
        ticketDTO.setIdClient(currentUser.getId());
        ticketService.add(ticketDTO);
        return ticketDTO;
    }
}
