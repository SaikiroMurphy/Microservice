package re.edu.inventory_service.event;

import java.time.LocalDateTime;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private Long orderId;

    private Long medicineId;

    private Integer quantity;

    private LocalDateTime timestamp;
}
