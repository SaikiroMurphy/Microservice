package re.edu.doctor_service.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDoctorRequest {
    private String fullName;

    private String specialization;

    private Integer experienceYears;

    private String email;

    private Boolean status;
}
