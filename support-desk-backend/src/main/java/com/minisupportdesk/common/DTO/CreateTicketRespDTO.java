package com.minisupportdesk.common.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketRespDTO {

    String message;

    String title;

    String createByName;
}
