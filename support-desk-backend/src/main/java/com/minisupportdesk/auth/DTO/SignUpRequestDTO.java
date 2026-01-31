package com.minisupportdesk.auth.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequestDTO {

    private String fullName;

    private String username;

    private String password;

}
