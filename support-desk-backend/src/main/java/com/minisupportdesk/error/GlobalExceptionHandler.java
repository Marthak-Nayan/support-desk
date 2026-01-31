package com.minisupportdesk.error;

import com.minisupportdesk.auth.DTO.SignUpResponseDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException ex) {
//        ApiError apiError = ApiError.builder()
//                .statusCode( HttpStatus.NOT_FOUND)
//                .message("Username not found with username")
//                .error(ex.getMessage())
//                .build();
//        return new ResponseEntity<>(apiError, apiError.getStatusCode());
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ApiError> handleBadCredentials(
//            BadCredentialsException ex) {
//
//        ApiError apiError = ApiError.builder()
//                .statusCode(HttpStatus.UNAUTHORIZED)
//                .error(ex.getMessage())
//                .message("Invalid username or password")
//                .build();
//        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
        ApiError apiError = ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.UNAUTHORIZED)
                .error(ex.getMessage())
                .message("Invalid username or password")
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException ex) {
        ApiError apiError = ApiError.builder()
                .error(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.UNAUTHORIZED)
                .message("Invalid JWT token")
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
//        ApiError apiError = new ApiError("Access denied: Insufficient permissions", HttpStatus.FORBIDDEN);
//        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
//    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError>handleIllegalArgumentException(EmailAlreadyExistsException ex){
        ApiError apiError = ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        ApiError apiError = ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(ex.getMessage())
                .message("An unexpected error occurred: ")
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
