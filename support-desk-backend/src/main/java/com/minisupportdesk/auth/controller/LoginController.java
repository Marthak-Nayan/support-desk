package com.minisupportdesk.auth.controller;

import com.minisupportdesk.auth.DTO.LoginRequestDTO;
import com.minisupportdesk.auth.DTO.LoginResponseDTO;
import com.minisupportdesk.auth.services.LoginServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginServices loginServices;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO req){
        return ResponseEntity.ok(loginServices.login(req));
    }

}
