package com.minisupportdesk.common.services;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.common.DTO.TicketMetricsRespDTO;
import com.minisupportdesk.entities.Role;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketMetricsServices {

    private final UserRepositary userRepositary;
    private final TicketRepositary ticketRepositary;

    public TicketMetricsRespDTO getMetrics(String username){
        User user = userRepositary.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("User Not found"));
        LocalDate todayDate = LocalDate.now();

        //Today Time range
        LocalDateTime startOfDay = todayDate.atStartOfDay();
        LocalDateTime endOfDay = todayDate.plusDays(1).atStartOfDay();

        //Week range
        LocalDate startOfWeek = todayDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = todayDate.with(DayOfWeek.SUNDAY);


        LocalDateTime startOfWeekDT = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekDT = endOfWeek.plusDays(1).atStartOfDay();

        if(user.getRole().equals(Role.ADMIN)){
            long todayCount = ticketRepositary.countByCreatedAtBetween(startOfDay, endOfDay);
            long weekCount = ticketRepositary.countByCreatedAtBetween(startOfWeekDT,endOfWeekDT);
            long openCount = ticketRepositary.countByStatus(Status.OPEN);
            long resolveCount = ticketRepositary.countByStatus(Status.RESOLVED);

            return TicketMetricsRespDTO.builder()
                    .openTickets(openCount)
                    .resolvedTickets(resolveCount)
                    .weekTickets(weekCount)
                    .todayTickets(todayCount)
                    .build();
        }
        if (user.getRole().equals(Role.USER)){
            long todayCount = ticketRepositary.countByCreatedByAndCreatedAtBetween(user,startOfDay, endOfDay);
            long weekCount = ticketRepositary.countByCreatedByAndCreatedAtBetween(user, startOfWeekDT,endOfWeekDT);
            long openCount = ticketRepositary.countByCreatedByAndStatus(user, Status.OPEN);
            long resolveCount = ticketRepositary.countByCreatedByAndStatus(user,Status.RESOLVED);

            return TicketMetricsRespDTO.builder()
                    .openTickets(openCount)
                    .resolvedTickets(resolveCount)
                    .weekTickets(weekCount)
                    .todayTickets(todayCount)
                    .build();
        }
        return TicketMetricsRespDTO.builder()
                .openTickets(0)
                .resolvedTickets(0)
                .weekTickets(0)
                .todayTickets(0)
                .build();
    }

}
