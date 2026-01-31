package com.minisupportdesk.users.DTO;

import com.minisupportdesk.entities.Priority;
import com.minisupportdesk.entities.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTicketUpdateReqDTO {
    private String title;

    private String description;

    private Status status;

    private Priority priority;
}
