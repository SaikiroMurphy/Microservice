package re.edu.appointment_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import re.edu.appointment_service.client.DoctorClient;
import re.edu.appointment_service.client.PatientClient;
import re.edu.appointment_service.model.dto.request.AppointmentRequest;
import re.edu.appointment_service.model.dto.response.AppointmentResponse;
import re.edu.appointment_service.model.entity.Appointment;
import re.edu.appointment_service.model.entity.AppointmentStatus;
import re.edu.appointment_service.repository.AppointmentRepository;

@Service
@RequiredArgsConstructor
public class AppointmentService {
        private final AppointmentRepository appointmentRepository;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    public List<AppointmentResponse> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Retry(name = "patientRetry", fallbackMethod = "patientFallback")
    public AppointmentResponse createAppointment(AppointmentRequest requestDTO) {
        // Kiểm tra xem Doctor-Service có đang hoạt động không, nếu không sẽ nhảy vào fallback của CircuitBreaker
        doctorClient.getAllDoctors();

        // Kiểm tra Patient-Service có hoạt động không, nếu lỗi sẽ retry 3 lần
        patientClient.getAllPatients();

        Appointment appointment = Appointment.builder()
                .patientId(requestDTO.getPatientId())
                .doctorId(requestDTO.getDoctorId())
                .appointmentDate(requestDTO.getAppointmentDate())
                .reason(requestDTO.getReason())
                .status(AppointmentStatus.valueOf(requestDTO.getStatus()))
                .build();

        Appointment saved = appointmentRepository.save(appointment);
        return toResponse(saved);
    }

    public AppointmentResponse patientFallback(AppointmentRequest requestDTO, Exception e) {
        throw new RuntimeException("Hiện tại không thể kiểm tra thông tin bệnh nhân, vui lòng thử lại sau vài giây");
    }

    // ── Mapper ──────────────────────────────────────────────────────────────
    private AppointmentResponse toResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentDate(appointment.getAppointmentDate())
                .reason(appointment.getReason())
                .status(appointment.getStatus().toString())
                .build();
    }
}
