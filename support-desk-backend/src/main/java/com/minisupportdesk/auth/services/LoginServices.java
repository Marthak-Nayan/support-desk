package com.minisupportdesk.auth.services;

import com.minisupportdesk.auth.DTO.LoginRequestDTO;
import com.minisupportdesk.auth.DTO.LoginResponseDTO;
import com.minisupportdesk.config.security.AuthUtils;
import com.minisupportdesk.entities.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServices {

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final AuthUtils authUtils;

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO req){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        log.info("User logged in: {}, {}",user.getUsername(),user.getRole().name());

        String token = authUtils.generateAccessToken(user);

        LoginResponseDTO response = LoginResponseDTO.builder()
                .userId(user.getId())
                .accessToken(token)
                .message("Login Successfully")
                .role(user.getRole().name())
                .build();

        return response;
    }
}
