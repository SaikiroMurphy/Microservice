package edu.customer_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.customer_service.model.dto.request.CustomerCreateRequest;
import edu.customer_service.model.dto.request.CustomerUpdateRequest;
import edu.customer_service.model.dto.response.CustomerResponse;
import edu.customer_service.model.dto.response.PageResponse;
import edu.customer_service.model.entity.Customer;
import edu.customer_service.model.mapper.CustomerMapper;
import edu.customer_service.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public PageResponse<CustomerResponse> getAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<CustomerResponse> customerResponses = customers.map(customerMapper::toResponse).getContent();

        return new PageResponse<>(
            customerResponses,
            customers.getNumber(),
            customers.getSize(),
            customers.getTotalElements(),
            customers.getTotalPages(),
            customers.isLast()
        );
    }

    public CustomerResponse getById(long id) {
        return customerMapper.toResponse(
                customerRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khách hàng với id phù hợp")));
    }

    public CustomerResponse create(CustomerCreateRequest createReq) {
        Customer customer = customerMapper.toEntity(createReq);
        customerRepository.save(customer);

        return customerMapper.toResponse(customer);
    }

    public CustomerResponse update(CustomerUpdateRequest updateReq, Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khách hàng với id phù hợp"));

        if (updateReq.getFullName() != null) {
            customer.setFullName(updateReq.getFullName());
        }

        if (updateReq.getEmail() != null) {
            customer.setEmail(updateReq.getEmail());
        }

        if (updateReq.getPassword() != null) {
            customer.setPassword(updateReq.getPassword());
        }

        customerRepository.save(customer);

        return customerMapper.toResponse(customer);
    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khách hàng với id phù hợp"));

        customerRepository.delete(customer);
    }
}
