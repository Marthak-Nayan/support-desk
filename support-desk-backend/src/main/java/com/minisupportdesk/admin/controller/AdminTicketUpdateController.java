package com.minisupportdesk.admin.controller;

import com.minisupportdesk.admin.DTO.AdminTicketUpdateReqDTO;
import com.minisupportdesk.admin.services.AdminTickUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminTicketUpdateController {

    private final AdminTickUpdateService adminTickUpdateService;

    @PutMapping("/updateTicket/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody AdminTicketUpdateReqDTO req, Authentication authentication){
        String username = authentication.getName();
        return adminTickUpdateService.updateTicket(id,req,username);
    }

}
