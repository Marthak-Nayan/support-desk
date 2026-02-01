package com.minisupportdesk.common.ticket.DTO;

import com.minisupportdesk.entities.Priority;
import com.minisupportdesk.entities.Status;
import com.minisupportdesk.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private UserDTO createdBy;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.title = ticket.getTitle();
        this.description = ticket.getDescription();
        this.status = ticket.getStatus();
        this.priority = ticket.getPriority();
        this.createdBy = new UserDTO(ticket.getCreatedBy());
    }
}
