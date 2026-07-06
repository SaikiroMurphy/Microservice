package edu.order_service.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.order_service.exception.InvalidOrderException;
import edu.order_service.exception.ServiceUnavailableException;
import edu.order_service.model.dto.request.OrderRequestDTO;
import edu.order_service.model.dto.response.OrderResponseDTO;
import edu.order_service.model.dto.response.ProductInfoResponseDTO;
import edu.order_service.model.entity.Order;
import edu.order_service.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO.getQuantity() == null || orderRequestDTO.getQuantity() <= 0) {
            throw new InvalidOrderException("Số lượng phải lớn hơn 0");
        }

        ProductInfoResponseDTO product = getProductFromDiscovery(orderRequestDTO.getProductId());

        Double totalAmount = orderRequestDTO.getQuantity() * product.getPrice();

        Order order = new Order();
        order.setCustomerId(orderRequestDTO.getCustomerId());
        order.setProductId(orderRequestDTO.getProductId());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);

        try {
            Order savedOrder = orderRepository.save(order);
            return convertToDTO(savedOrder);
        } catch (Exception e) {
            throw e;
        }
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Đơn hàng với id " + id + " không tồn tại"));
        return convertToDTO(order);
    }

    private ProductInfoResponseDTO getProductFromDiscovery(Long productId) {
        String productUrl = "http://PRODUCT-SERVICE/api/v1/products/" + productId;
        try {
            return restTemplate.getForObject(productUrl, ProductInfoResponseDTO.class);
        } catch (RestClientException ex) {
            throw new ServiceUnavailableException("PRODUCT-SERVICE hiện không khả dụng");
        }
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
