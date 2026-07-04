package edu.order_service.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInfoResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer stockQuantity;
}
