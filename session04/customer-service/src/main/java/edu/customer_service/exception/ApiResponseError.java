package edu.customer_service.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponseError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
}
