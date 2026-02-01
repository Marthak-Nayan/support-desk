package com.minisupportdesk.common.dashboard.controller;

import com.minisupportdesk.common.dashboard.DTO.TicketMetricsRespDTO;
import com.minisupportdesk.common.dashboard.service.TicketMetricsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketMetricsController {

    private final TicketMetricsServices ticketMetricsServices;

    @GetMapping("/getTicketMatrix")
    public TicketMetricsRespDTO getMetrics(Authentication authentication){
        String username = authentication.getName();
        return ticketMetricsServices.getMetrics(username);
    }

}
