package edu.customer_service.model.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private T data;
    private Object errors;

    public static <T> ApiResponse<T> success(Integer status, String message, T data) {
        return new ApiResponse<>(LocalDateTime.now(), status, message, data, null);
    }

    public static ApiResponse<Void> error(Integer status, String message, Object errors) {
        return new ApiResponse<>(LocalDateTime.now(), status, message, null, errors);
    }
}
