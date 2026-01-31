package com.minisupportdesk.admin.services;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.admin.DTO.AdminTicketUpdateReqDTO;
import com.minisupportdesk.entities.Ticket;
import com.minisupportdesk.entities.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminTickUpdateService {

    private final UserRepositary userRepositary;
    private final TicketRepositary ticketRepositary;

    public ResponseEntity<?> updateTicket(Long id, AdminTicketUpdateReqDTO req,String username){
        Ticket ticket = ticketRepositary.findById(id).orElseThrow(()-> new IllegalArgumentException("Ticket Not found"));;

        if(req.getTitle() != null){
            ticket.setTitle(req.getTitle());
        }
        if(req.getDescription() != null){
            ticket.setDescription(req.getDescription());
        }
        if(req.getStatus() != null){
            ticket.setStatus(req.getStatus());
        }
        if(req.getPriority() != null){
            ticket.setPriority(req.getPriority());
        }
        ticketRepositary.save(ticket);

        return ResponseEntity.ok("Ticket Update Successfully");
    }

}
