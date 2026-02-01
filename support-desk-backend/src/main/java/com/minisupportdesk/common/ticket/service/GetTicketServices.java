package com.minisupportdesk.common.ticket.service;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.common.ticket.DTO.TicketDto;
import com.minisupportdesk.common.ticket.DTO.TicketRespDTO;
import com.minisupportdesk.entities.Priority;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.Ticket;
import com.minisupportdesk.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetTicketServices {

    private final Map<String, TicketFetchStrategy> strategyMap;

    private final UserRepositary userRepositary;
    private final TicketRepositary ticketRepositary;


    public TicketRespDTO getAllOrFilterTicket(Pageable pageable, List<Status> status,
                                              List<Priority> priority, String username){

        log.info("{}{}{}{}",pageable,status,priority,username);
        User user = userRepositary.findByUsername(username)
                .orElseThrow(()-> new IllegalArgumentException("User Not found"));

        TicketFetchStrategy strategy = strategyMap.get(user.getRole().name()+"_TICKET");


        if(strategy == null){
            throw new IllegalStateException(
                    "No ticket strategy for role: " + user.getRole()+"_TICKET");
        }

        Page<Ticket> tickets = strategy.fetchTickets(user.getId(), pageable, status, priority);

        List<TicketDto> safeTickets = tickets.getContent()
                .stream()
                .map(TicketDto::new)
                .toList();

        return TicketRespDTO.builder()
                .ticketList(safeTickets)
                .pageNumber(tickets.getNumber())
                .totalElements(tickets.getTotalElements())
                .empty(tickets.isEmpty())
                .totalPages(tickets.getTotalPages())
                .size(tickets.getSize())
                .build();
    }
}
