package re.edu.pharmacy_service.model.mapper;

import org.mapstruct.*;

import re.edu.pharmacy_service.model.dto.request.CreateBillDetailRequest;
import re.edu.pharmacy_service.model.dto.response.BillDetailResponse;
import re.edu.pharmacy_service.model.entity.BillDetail;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillDetailMapper {
    BillDetail toEntity(CreateBillDetailRequest request);

    BillDetailResponse toResponse(BillDetail billDetail);
}
