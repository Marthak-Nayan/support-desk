package com.minisupportdesk.common.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketMetricsRespDTO {

    private long todayTickets;

    private long weekTickets;

    private long openTickets;

    private long resolvedTickets;

}
