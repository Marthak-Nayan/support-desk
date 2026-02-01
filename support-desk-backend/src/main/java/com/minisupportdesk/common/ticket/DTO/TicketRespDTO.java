package com.minisupportdesk.common.ticket.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRespDTO {

    private List<TicketDto> ticketList;

    private int pageNumber;

    private int size;

    private Long totalElements;

    private int totalPages;

    private boolean empty;

}
