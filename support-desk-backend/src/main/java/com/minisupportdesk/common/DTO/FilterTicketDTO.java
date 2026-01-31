package com.minisupportdesk.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterTicketDTO {

    private List<String> statuses;

    private List<String> priorities;

}
