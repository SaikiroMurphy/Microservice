package re.edu.doctor_service.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorResponse {
    private Long id;

    private String fullName;

    private String specialization;

    private Integer experienceYears;

    private String email;

    private Boolean status;
}
