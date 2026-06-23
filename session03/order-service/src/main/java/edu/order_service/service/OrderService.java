package edu.order_service.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import edu.order_service.exception.InvalidOrderException;
import edu.order_service.model.dto.request.OrderRequestDTO;
import edu.order_service.model.dto.response.OrderResponseDTO;
import edu.order_service.model.entity.Order;
import edu.order_service.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    // Simulated product price (in real scenario, would fetch from Product Service)
    private static final Double PRODUCT_PRICE = 100.0;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        // Validate quantity
        if (orderRequestDTO.getQuantity() == null || orderRequestDTO.getQuantity() <= 0) {
            throw new InvalidOrderException("Số lượng phải lớn hơn 0");
        }

        // Calculate total amount (simulated: quantity * price)
        Double totalAmount = orderRequestDTO.getQuantity() * PRODUCT_PRICE;

        // Create and save order
        Order order = new Order();
        order.setCustomerId(orderRequestDTO.getCustomerId());
        order.setProductId(orderRequestDTO.getProductId());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);

        try {
            Order savedOrder = orderRepository.save(order);
            return convertToDTO(savedOrder);
        } catch (Exception e) {
            throw e; // This will be caught by GlobalExceptionHandler and return 500
        }
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Đơn hàng với id " + id + " không tồn tại"));
        return convertToDTO(order);
    }

    private OrderResponseDTO convertToDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setProductId(order.getProductId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        return dto;
    }
}
