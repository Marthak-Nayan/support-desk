package com.minisupportdesk.common.controller;

import com.minisupportdesk.common.DTO.FilterTicketDTO;
import com.minisupportdesk.common.DTO.TicketRespDTO;
import com.minisupportdesk.common.services.GetTicketServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class GetTicketController {

    private final GetTicketServices getTicketServices;

    @GetMapping("/getTickets")
    public TicketRespDTO getAllTickets(Pageable pageable, Authentication authentication){
        String username = authentication.getName();
        return  getTicketServices.getAllTicket(pageable,username);
    }
}
