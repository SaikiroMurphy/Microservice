package session02.session02.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import session02.session02.model.dto.ProductResponseDTO;
import session02.session02.model.entity.Product;
import session02.session02.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDTO getProductDtoById(Long id) {
        Optional<Product> opt = productRepository.findById(id);
        Product p = opt.orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDto(p);
    }

    public ProductResponseDTO mapToDto(Product product) {
        return new ProductResponseDTO(product.getId(), product.getName(), product.getSellPrice());
    }
}
