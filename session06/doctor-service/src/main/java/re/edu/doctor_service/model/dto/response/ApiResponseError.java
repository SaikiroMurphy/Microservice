package re.edu.doctor_service.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponseError<T> {
    private int status;
    private String message;
    private T data;
}
