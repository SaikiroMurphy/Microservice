package edu.customer_service.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.customer_service.model.dto.request.CustomerCreateRequest;
import edu.customer_service.model.dto.response.CustomerResponse;
import edu.customer_service.model.entity.Customer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer toEntity(CustomerCreateRequest createReq);
    CustomerResponse toResponse(Customer entity);
}
