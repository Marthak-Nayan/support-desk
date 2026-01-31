package com.minisupportdesk.auth.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "Username must be required")
    private String username;

    @NotBlank(message = "Password must be required")
    private String password;

}
