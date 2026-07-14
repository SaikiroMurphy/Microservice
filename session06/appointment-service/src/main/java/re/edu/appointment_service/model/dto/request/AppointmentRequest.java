package re.edu.appointment_service.model.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AppointmentRequest {

    @NotNull(message = "ID bệnh nhân không được để trống")
    private Long patientId;

    @NotNull(message = "ID bác sĩ không được để trống")
    private Long doctorId;

    @NotNull(message = "Ngày giờ hẹn khám không được để trống")
    private LocalDateTime appointmentDate;

    @NotBlank(message = "Lý do khám bệnh không được để trống")
    @Size(max = 500, message = "Lý do khám bệnh tối đa 500 ký tự")
    private String reason;

    @NotBlank(message = "Trạng thái không được để trống")
    @Pattern(regexp = "^(PENDING|CONFIRMED|CANCELLED)$", message = "Trạng thái phải là: PENDING, CONFIRMED hoặc CANCELLED")
    private String status;
}
