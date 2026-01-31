package com.minisupportdesk.users.controller;

import com.minisupportdesk.users.DTO.UserTicketUpdateReqDTO;
import com.minisupportdesk.users.services.UserTickUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserTicketUpdateController {

    private final UserTickUpdateService userTickUpdateService;

    @PutMapping("/updateTicket/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody UserTicketUpdateReqDTO req, Authentication authentication){
        String username = authentication.getName();
        return userTickUpdateService.updateTicket(id,req,username);
    }

}
