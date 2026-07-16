package re.edu.appointment_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import re.edu.appointment_service.model.dto.request.AppointmentRequest;
import re.edu.appointment_service.model.dto.response.ApiResponseError;
import re.edu.appointment_service.model.dto.response.AppointmentResponse;
import re.edu.appointment_service.service.AppointmentService;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    /**
     * GET /api/appointments
     * Lấy danh sách tất cả lịch hẹn.
     */
    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    /**
     * POST /api/appointments
     * Tạo mới một lịch hẹn.
     */
    @PostMapping
    @CircuitBreaker(name = "doctorServiceCB", fallbackMethod = "getDoctorFallback")
    public ResponseEntity<?> createAppointment(
            @Valid @RequestBody AppointmentRequest request) {
        AppointmentResponse created = appointmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    public ResponseEntity<?> getDoctorFallback(AppointmentRequest request, Exception e) {
        if (e instanceof RuntimeException && e.getMessage().contains("Patient Service Error")) {
            ApiResponseError error = ApiResponseError.builder()
                    .message(e.getMessage())
                    .status(503)
                    .error("Patient Service Error")
                    .timestamp(java.time.LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
        }

        ApiResponseError error = ApiResponseError.builder()
                .message("Hiện tại không thể kiểm tra thông tin bác sĩ, vui lòng thử lại sau vài giây")
                .status(503)
                .error("Doctor Service Error")
                .timestamp(java.time.LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }
}
