package com.minisupportdesk.admin.DTO;

import com.minisupportdesk.entities.Priority;
import com.minisupportdesk.entities.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminTicketUpdateReqDTO {
    private String title;

    private String description;

    private Status status;

    private Priority priority;
}
