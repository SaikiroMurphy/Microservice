package re.edu.pharmacy_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "warehouse-service", url = "${warehouse-service.url}")
public interface WarehouseClient {
    @GetMapping
    List<Object> checkStock(Long drugId);
}
