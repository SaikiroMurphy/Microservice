package session02.session02.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private BigDecimal sellPrice;
}
