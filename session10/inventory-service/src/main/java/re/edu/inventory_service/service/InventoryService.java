package re.edu.inventory_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import re.edu.inventory_service.event.OrderEvent;
import re.edu.inventory_service.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class InventoryService {
    private final ProductRepository productRepository;

    @Transactional
    public void decreaseStock(OrderEvent event) {

        productRepository.decreaseStock(
                event.getMedicineId(),
                event.getQuantity()
        );
    }
}
