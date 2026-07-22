package re.edu.pharmacy_service.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import re.edu.pharmacy_service.client.WarehouseClient;
import re.edu.pharmacy_service.model.dto.request.CreateBillRequest;
import re.edu.pharmacy_service.model.dto.response.BillResponse;
import re.edu.pharmacy_service.service.BillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/pharmacy")
@RequiredArgsConstructor
@RefreshScope
public class BillController {

    private final BillService billService;
    // private final WarehouseClient warehouseClient;

    @PostMapping("/bill")
    public ResponseEntity<BillResponse> create(
            @Valid @RequestBody CreateBillRequest request) {

        return ResponseEntity.ok(billService.create(request));
    }

    // @GetMapping("/warehouse")
    // @CircuitBreaker(name = "warehouseCB", fallbackMethod = "checkWarehouseFallback")
    // public ResponseEntity<Object> getStorage(@RequestParam String param) {
    //     return ResponseEntity.ok(warehouseClient.checkStock(null));
    // }
    
    // public ResponseEntity<Object> checkWarehouseFallback(String param, Throwable throwable) {
    //     return ResponseEntity.status(503).body("Không thể kết nối kho tổng. Hệ thống sẽ sử dụng dữ liệu tồn kho cục bộ để tiếp tục giao dịch");
    // }
}
