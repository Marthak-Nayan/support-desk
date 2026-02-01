package com.minisupportdesk.common.dashboard.service;

import com.minisupportdesk.common.dashboard.DTO.TicketMetricsRespDTO;
import com.minisupportdesk.entities.Role;
import com.minisupportdesk.entities.User;

public interface MetricsService {

    TicketMetricsRespDTO getMetrics(User user);

    public Role supports();

}
