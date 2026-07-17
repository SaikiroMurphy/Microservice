package re.edu.pharmacy_service.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import re.edu.pharmacy_service.model.dto.request.CreateBillRequest;
import re.edu.pharmacy_service.model.dto.response.BillResponse;
import re.edu.pharmacy_service.service.BillService;

@RestController
@RequestMapping("/api/v1/bill")
@RequiredArgsConstructor
@RefreshScope
public class BillController {

    private final BillService billService;

    @PostMapping
    public ResponseEntity<BillResponse> create(
            @Valid @RequestBody CreateBillRequest request) {

        return ResponseEntity.ok(billService.create(request));
    }
}
