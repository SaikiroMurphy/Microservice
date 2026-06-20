package session02.session02.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import session02.session02.model.entity.Product;
import session02.session02.model.entity.Order;
import session02.session02.repository.ProductRepository;
import session02.session02.repository.OrderRepository;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public SampleDataLoader(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() > 0) {
            return;
        }

        Product p1 = new Product();
        p1.setName("Wireless Mouse");
        p1.setSku("WM-1001");
        p1.setImportPrice(new BigDecimal("8.50"));
        p1.setSellPrice(new BigDecimal("19.99"));
        p1.setStockQuantity(120);

        Product p2 = new Product();
        p2.setName("Mechanical Keyboard");
        p2.setSku("MK-2002");
        p2.setImportPrice(new BigDecimal("25.00"));
        p2.setSellPrice(new BigDecimal("59.95"));
        p2.setStockQuantity(60);

        Product p3 = new Product();
        p3.setName("USB-C Hub");
        p3.setSku("UH-3003");
        p3.setImportPrice(new BigDecimal("10.00"));
        p3.setSellPrice(new BigDecimal("29.50"));
        p3.setStockQuantity(200);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);

        // Seed some orders for testing (Order.id is not generated)
        if (orderRepository.count() == 0) {
            Order o1 = new Order();
            o1.setId(1);
            Order o2 = new Order();
            o2.setId(2);
            orderRepository.save(o1);
            orderRepository.save(o2);
        }
    }
}
