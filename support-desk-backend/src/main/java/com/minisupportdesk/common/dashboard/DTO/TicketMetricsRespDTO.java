package com.minisupportdesk.common.dashboard.DTO;

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
