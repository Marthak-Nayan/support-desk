package com.minisupportdesk.common.DTO;

import com.minisupportdesk.entities.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketReqDTO {

    private String title;

    private String description;

    private Priority priority;

}
