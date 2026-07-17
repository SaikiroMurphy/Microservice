package re.edu.pharmacy_service.model.mapper;

import org.mapstruct.*;

import re.edu.pharmacy_service.model.dto.request.*;
import re.edu.pharmacy_service.model.dto.response.BillResponse;
import re.edu.pharmacy_service.model.entity.Bill;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillMapper {

    Bill toEntity(CreateBillRequest request);

    BillResponse toResponse(Bill bill);

    void update(UpdateBillRequest request,
            @MappingTarget Bill bill);
}
