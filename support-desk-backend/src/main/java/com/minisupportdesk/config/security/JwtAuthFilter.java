package com.minisupportdesk.config.security;

import com.minisupportdesk.Repository.UserRepositary;
import com.minisupportdesk.entities.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtils authUtils;
    private final UserRepositary userRepositary;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        log.info("Incoming Request: {}",path);

        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new BadCredentialsException("JWT token is missing");
            }

            String token = authHeader.substring(7);

            String username = authUtils.getUsernameFromToken(token);

            User user = userRepositary.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        catch (BadCredentialsException ex) {
            log.warn("JWT missing: {}", ex.getMessage());
            sendUnauthorized(response, "UNAUTHORIZED", ex.getMessage());
            return;
        }
        catch (ExpiredJwtException ex) {
            log.warn("JWT expired: {}", ex.getMessage());
            sendUnauthorized(response, "TOKEN_EXPIRED", "JWT token has expired");
            return;
        }
        catch (JwtException ex) {
            log.warn("Invalid JWT: {}", ex.getMessage());
            sendUnauthorized(response, "INVALID_TOKEN", "JWT token is invalid");
            return;
        }
        catch (UsernameNotFoundException ex) {
            log.warn("User not found: {}", ex.getMessage());
            sendUnauthorized(response, "UNAUTHORIZED", ex.getMessage());
            return;
        }
        catch (Exception ex) {
            log.error("Unexpected authentication error", ex);
            sendUnauthorized(response, "AUTH_ERROR", "Authentication failed");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String error, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("""
                {
                  "error": "%s",
                  "message": "%s"
                }
                """.formatted(error, message));
    }
}
