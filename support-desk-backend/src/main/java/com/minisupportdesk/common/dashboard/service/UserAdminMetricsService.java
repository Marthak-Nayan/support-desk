package com.minisupportdesk.common.dashboard.service;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.common.dashboard.DTO.TicketMetricsRespDTO;
import com.minisupportdesk.common.utils.DateRangeUtil;
import com.minisupportdesk.entities.Role;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component()
@RequiredArgsConstructor
public class UserAdminMetricsService implements MetricsService{

    private final TicketRepositary ticketRepositary;
    private final DateRangeUtil dateRangeUtil;

    @Override
    public TicketMetricsRespDTO getMetrics(User user) {
        LocalDateTime startToday = DateRangeUtil.getStartOfToday();
        LocalDateTime endToday = DateRangeUtil.getEndOfToday();

        LocalDateTime startWeek = DateRangeUtil.getStartOfWeek();
        LocalDateTime endWeek = DateRangeUtil.getEndOfWeek();

        long todayCount = ticketRepositary.countByCreatedByAndCreatedAtBetween(user,startToday, endToday);
        long weekCount = ticketRepositary.countByCreatedByAndCreatedAtBetween(user, startWeek,endWeek);
        long openCount = ticketRepositary.countByCreatedByAndStatus(user, Status.OPEN);
        long resolveCount = ticketRepositary.countByCreatedByAndStatus(user,Status.RESOLVED);

        return TicketMetricsRespDTO.builder()
                .openTickets(openCount)
                .resolvedTickets(resolveCount)
                .weekTickets(weekCount)
                .todayTickets(todayCount)
                .build();
    }

    @Override
    public Role supports() {
        return Role.USER;
    }
}
