package edu.order_service.exception;

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

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ApiResponseError> handleInvalidOrderException(InvalidOrderException e) {
        ApiResponseError error = new ApiResponseError(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Invalid order",
            e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseError> handleGeneralException(Exception e) {
        ApiResponseError error = new ApiResponseError(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            "Không thể lưu đơn hàng vào cơ sở dữ liệu"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
