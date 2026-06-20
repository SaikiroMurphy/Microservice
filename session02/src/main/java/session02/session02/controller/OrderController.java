package session02.session02.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import session02.session02.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import session02.session02.model.entity.Order;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/health-check")
    public String getMethodName() {
        return new String("Order Service is Up");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
}
