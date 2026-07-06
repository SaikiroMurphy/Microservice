package edu.product_service.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDTO {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @Min(value = 1, message = "Giá phải lớn hơn 0")
    private Double price;

    @Min(value = 0, message = "Số lượng tồn kho không được âm")
    private Integer stockQuantity;
}
