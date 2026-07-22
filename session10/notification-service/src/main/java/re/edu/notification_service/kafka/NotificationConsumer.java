package re.edu.notification_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import re.edu.notification_service.event.OrderEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    @KafkaListener(topics = "medicine-stock-events", groupId = "notification-group")
    public void receive(OrderEvent event) {

        log.info("Received OrderEvent: {}", event);

        log.info("Hóa đơn cho đơn hàng {} đã được gửi tới khách hàng.",
                event.getOrderId());
    }
}