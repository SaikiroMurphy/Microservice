package edu.customer_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleEntityNotFoundException(EntityNotFoundException e) {
        ApiResponseError error = new ApiResponseError(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Entity not found",
            e.getLocalizedMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
