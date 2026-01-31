package com.minisupportdesk.config.security;

import com.minisupportdesk.Repository.UserRepositary;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoutomUserDetailsService implements UserDetailsService {


    private final UserRepositary userRepositary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositary.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not Found"));
    }
}
