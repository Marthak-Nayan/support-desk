package com.minisupportdesk.users.services;

import com.minisupportdesk.Repository.TicketRepositary;
import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.Ticket;
import com.minisupportdesk.entities.User;
import com.minisupportdesk.users.DTO.UserTicketUpdateReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTickUpdateService {

    private final UserRepositary userRepositary;
    private final TicketRepositary ticketRepositary;

    public ResponseEntity<?> updateTicket(Long id, UserTicketUpdateReqDTO req, String username){
        User user = userRepositary.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("User Not found"));
        Ticket ticket = ticketRepositary.findById(id).orElseThrow(()-> new IllegalArgumentException("Ticket Not found"));;

        if(ticket.getStatus()!= Status.OPEN && ticket.getStatus()!= Status.IN_PROGRESS){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You can edit ticket only when status is OPEN or IN_PROGRESS");
        }
        if(!ticket.getCreatedBy().getId().equals(user.getId()))
        {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You can edit only your own tickets");
        }

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
