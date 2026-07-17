package re.edu.pharmacy_service.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateBillRequest {

    @NotNull
    private Long customerId;

    @Valid
    private List<CreateBillDetailRequest> billDetails;
}