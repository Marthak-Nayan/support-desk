package com.minisupportdesk.auth.controller;

import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.auth.DTO.AdminCreateDTO;
import com.minisupportdesk.auth.DTO.SignUpResponseDTO;
import com.minisupportdesk.auth.DTO.SignUpRequestDTO;
import com.minisupportdesk.auth.services.SignUpServices;
import com.minisupportdesk.entities.Role;
import com.minisupportdesk.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SignUpController {

    private final SignUpServices signUpServices;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositary userRepositary;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO req){
            return ResponseEntity.ok(signUpServices.signUp(req));
    }

    @PostMapping("/create-admin")
    public String createAdmin(@RequestBody AdminCreateDTO req) {
        User admin = User.builder()
                .fullName(req.getFullName())
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepositary.save(admin);
        return "Admin created";
    }
}
