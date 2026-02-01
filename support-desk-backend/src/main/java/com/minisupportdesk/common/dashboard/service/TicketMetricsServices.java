package com.minisupportdesk.common.dashboard.service;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.common.dashboard.DTO.TicketMetricsRespDTO;
import com.minisupportdesk.entities.Role;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketMetricsServices {

    private final TicketStrategyRegistry ticketStrategyRegistry;
    private final UserRepositary userRepositary;
    private final TicketRepositary ticketRepositary;

    public TicketMetricsRespDTO getMetrics(String username){
        User user = userRepositary.findByUsername(username)
                .orElseThrow(()-> new IllegalArgumentException("User Not found"));


        MetricsService dashBoard = ticketStrategyRegistry.getRoleService(user.getRole());

        TicketMetricsRespDTO metricsRespDTO = dashBoard.getMetrics(user);

        if (metricsRespDTO != null){
            return metricsRespDTO;
        }

        return TicketMetricsRespDTO.builder()
                .openTickets(0)
                .resolvedTickets(0)
                .weekTickets(0)
                .todayTickets(0)
                .build();
    }

}
