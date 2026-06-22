package edu.customer_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.customer_service.model.dto.request.CustomerCreateRequest;
import edu.customer_service.model.dto.request.CustomerUpdateRequest;
import edu.customer_service.model.dto.response.ApiResponse;
import edu.customer_service.model.dto.response.CustomerResponse;
import edu.customer_service.model.dto.response.PageResponse;
import edu.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CustomerResponse>>> getAll(
        @PageableDefault(
                page = 0,
                size = 10,
                sort = "id",
                direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        PageResponse<CustomerResponse> customers = (customerService.getAll(pageable));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, "Lấy danh sách khách hàng thành công", customers));
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CustomerResponse>> create(@ModelAttribute @Valid CustomerCreateRequest createReq) {
        CustomerResponse res = customerService.create(createReq);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, "Tạo khách hàng mới thành công", res));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> update(@ModelAttribute @Valid CustomerUpdateRequest updateReq, @PathVariable Long id) {
        CustomerResponse res = customerService.update(updateReq, id);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, "Cập nhật thông tin khách hàng thành công", res));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getById(@PathVariable Long id) {
        CustomerResponse res = customerService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, "Lấy thông tin khách hàng thành công", res));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        customerService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
