package com.minisupportdesk.auth.DTO;

import com.minisupportdesk.entities.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {

    private String accessToken;

    private Long userId;

    private String message;

    private String role;

}
