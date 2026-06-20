package session02.session02.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import session02.session02.model.dto.ProductResponseDTO;
import session02.session02.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        try {
            ProductResponseDTO dto = productService.getProductDtoById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
