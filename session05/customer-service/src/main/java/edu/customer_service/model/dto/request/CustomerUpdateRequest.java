package edu.customer_service.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerUpdateRequest {
    private String fullName;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String password;
}
