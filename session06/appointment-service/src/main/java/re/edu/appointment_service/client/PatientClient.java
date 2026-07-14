package re.edu.appointment_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "patient-service", url = "${patient-service.url}")
public interface PatientClient {

    @GetMapping("/api/v1/patients")
    List<Object> getAllPatients();
}
