package com.minisupportdesk.common.dashboard.service;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.common.dashboard.DTO.TicketMetricsRespDTO;
import com.minisupportdesk.common.utils.DateRangeUtil;
import com.minisupportdesk.entities.Role;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component()
@RequiredArgsConstructor
public class AdminTicketMetricsService implements MetricsService{

    private final TicketRepositary ticketRepositary;

    @Override
    public TicketMetricsRespDTO getMetrics(User user) {

        LocalDateTime startToday = DateRangeUtil.getStartOfToday();
        LocalDateTime endToday = DateRangeUtil.getEndOfToday();

        LocalDateTime startWeek = DateRangeUtil.getStartOfWeek();
        LocalDateTime endWeek = DateRangeUtil.getEndOfWeek();

        long todayCount = ticketRepositary.countByCreatedAtBetween(startToday, endToday);
        long weekCount = ticketRepositary.countByCreatedAtBetween(startWeek,endWeek);
        long openCount = ticketRepositary.countByStatus(Status.OPEN);
        long resolveCount = ticketRepositary.countByStatus(Status.RESOLVED);

        return TicketMetricsRespDTO.builder()
                .openTickets(openCount)
                .resolvedTickets(resolveCount)
                .weekTickets(weekCount)
                .todayTickets(todayCount)
                .build();
    }

    @Override
    public Role supports() {
        return Role.ADMIN;
    }
}
