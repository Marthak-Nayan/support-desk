package com.minisupportdesk.common.ticket.service;

import com.minisupportdesk.entities.Priority;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketFetchStrategy {

    Page<Ticket> fetchTickets(Long userId, Pageable pageable, List<Status> statuses, List<Priority> priorities);

}
