package com.minisupportdesk.auth.services;

import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.auth.DTO.SignUpResponseDTO;
import com.minisupportdesk.auth.DTO.SignUpRequestDTO;
import com.minisupportdesk.entities.Role;
import com.minisupportdesk.entities.User;
import com.minisupportdesk.error.EmailAlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpServices {

    private final ModelMapper modelMapper;
    private final UserRepositary userRepositary;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponseDTO signUp(SignUpRequestDTO req){
        Optional<User> existingUser = userRepositary.findByUsername(req.getUsername());

        if(existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = userRepositary.save(User.builder()
                .fullName(req.getFullName())
                .role(Role.USER)
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .build()
        );

        SignUpResponseDTO response = modelMapper.map(user, SignUpResponseDTO.class);
        response.setMessage("User registered successfully");
        return response;
    }
}
