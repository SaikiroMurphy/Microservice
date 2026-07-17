package re.edu.pharmacy_service.model.dto.request;


import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBillRequest {

    @NotNull
    private Long customerId;

    @NotEmpty
    @Valid
    private List<CreateBillDetailRequest> billDetails;

}
