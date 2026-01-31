package com.minisupportdesk.common.services;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.common.DTO.FilterTicketDTO;
import com.minisupportdesk.common.DTO.TicketDto;
import com.minisupportdesk.common.DTO.TicketRespDTO;
import com.minisupportdesk.entities.Ticket;
import com.minisupportdesk.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTicketServices {

    private final UserRepositary userRepositary;
    private final TicketRepositary ticketRepositary;

    public TicketRespDTO getAllTicket(Pageable pageable, String username){
        User user = userRepositary.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("User Not found"));

        Page<Ticket> tickets = null;

        if(user.getRole().name().equals("ADMIN")){
            tickets = ticketRepositary.findAll(pageable);
        }
        if (user.getRole().name().equals("USER")) {
            tickets = ticketRepositary.findByCreatedById(user.getId(),pageable);
        }

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
