package com.minisupportdesk.common.controller;

import com.minisupportdesk.common.DTO.CreateTicketReqDTO;
import com.minisupportdesk.common.DTO.CreateTicketRespDTO;
import com.minisupportdesk.common.services.CreateTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/createTicket")
@RequiredArgsConstructor
public class CreateTicket {

    private final CreateTicketService createTicketService;

    @PostMapping
    public ResponseEntity<CreateTicketRespDTO> createTicket(@RequestBody CreateTicketReqDTO req, Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(createTicketService.createTicket(req,username));
    }

}
