package com.minisupportdesk.auth.controller;

import com.minisupportdesk.auth.DTO.LoginRequestDTO;
import com.minisupportdesk.auth.DTO.LoginResponseDTO;
import com.minisupportdesk.auth.services.LoginServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO req){
        LoginResponseDTO responseDTO = loginServices.login(req);

        ResponseCookie cookie = ResponseCookie.from("access_token", responseDTO.getAccessToken())
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(600)
                .build();

        LoginResponseDTO response = LoginResponseDTO.builder()
                .message("Login Successfully")
                .role(responseDTO.getRole())
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,cookie.toString())
                .body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        ResponseCookie cookie = ResponseCookie.from("access_token", "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,cookie.toString())
                .body("Logout Successfully");
    }

}
