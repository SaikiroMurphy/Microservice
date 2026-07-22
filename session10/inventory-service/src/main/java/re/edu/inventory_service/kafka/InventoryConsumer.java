package re.edu.inventory_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import re.edu.inventory_service.event.OrderEvent;
import re.edu.inventory_service.service.InventoryService;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "medicine-stock-events", groupId = "inventory-group")
    public void consume(OrderEvent event) {

        log.info("Received OrderEvent: {}", event);

        inventoryService.decreaseStock(event);

        log.info("Stock updated successfully.");
    }
}
