package re.edu.appointment_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "doctor-service", url = "${doctor-service.url}")
public interface DoctorClient {
    @GetMapping("/api/v1/doctors")
    List<Object> getAllDoctors();
}
