package re.edu.pharmacy_service.model.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BillResponse {

    private Long id;

    private Long customerId;

    private BigDecimal totalAmount;

    private BigDecimal vatRate;

    private BigDecimal vatAmount;

    private BigDecimal finalAmount;

    private LocalDateTime createdAt;

    private List<BillDetailResponse> billDetails;
}
