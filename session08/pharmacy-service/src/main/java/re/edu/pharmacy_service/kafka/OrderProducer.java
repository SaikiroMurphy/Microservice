package re.edu.pharmacy_service.kafka;

import lombok.RequiredArgsConstructor;
import re.edu.pharmacy_service.event.OrderEvent;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private static final String TOPIC = "medicine-stock-events";

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void send(OrderEvent event) {

        // Message Key = medicineId
        kafkaTemplate.send(
                TOPIC,
                event.getMedicineId().toString(),
                event);
    }
}
