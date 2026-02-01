package com.minisupportdesk.common.dashboard.service;

import com.minisupportdesk.entities.Role;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class TicketStrategyRegistry {

    private final Map<Role, MetricsService> metricsServiceMap;

    public TicketStrategyRegistry(List<MetricsService> metricsServices){
        Map<Role, MetricsService> map = new EnumMap<>(Role.class);
        for(MetricsService service : metricsServices){
            map.put(service.supports(),service);
        }
        this.metricsServiceMap =map;
    }

    public MetricsService getRoleService(Role role){
        return metricsServiceMap.get(role);
    }
}
