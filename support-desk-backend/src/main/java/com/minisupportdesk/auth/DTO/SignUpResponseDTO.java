package com.minisupportdesk.auth.DTO;

import com.minisupportdesk.entities.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponseDTO {

    private Long id;

    private String username;

    private Role role;

    private String message;

}
