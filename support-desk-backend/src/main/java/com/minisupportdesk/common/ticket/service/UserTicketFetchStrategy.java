package com.minisupportdesk.common.ticket.service;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.entities.Priority;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("USER_TICKET")
@RequiredArgsConstructor
public class UserTicketFetchStrategy implements TicketFetchStrategy{

    private final TicketRepositary ticketRepositary;

    @Override
    public Page<Ticket> fetchTickets(Long userId, Pageable pageable, List<Status> statuses, List<Priority> priorities) {
        boolean hasStatus = statuses != null && !statuses.isEmpty();
        boolean hasPriority = priorities != null && !priorities.isEmpty();

        if(hasStatus && hasPriority){
            return ticketRepositary.findAllByCreatedByIdAndStatusInAndPriorityIn(userId, statuses, priorities, pageable);
        }
        if (hasStatus){
            return ticketRepositary.findAllByCreatedByIdAndStatusIn(userId, statuses, pageable);
        }
        if (hasPriority){
            return ticketRepositary.findAllByCreatedByIdAndPriorityIn(userId, priorities, pageable);
        }

        return ticketRepositary.findAllByCreatedById(userId, pageable);
    }
}
