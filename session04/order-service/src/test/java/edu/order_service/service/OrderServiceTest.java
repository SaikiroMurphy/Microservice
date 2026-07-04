package edu.order_service.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.order_service.exception.ServiceUnavailableException;
import edu.order_service.model.dto.request.OrderRequestDTO;
import edu.order_service.model.dto.response.ProductInfoResponseDTO;
import edu.order_service.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, restTemplate);
    }

    @Test
    void createOrder_whenProductServiceUnavailable_shouldThrowServiceUnavailableException() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setCustomerId(1L);
        request.setProductId(10L);
        request.setQuantity(2);

        when(restTemplate.getForObject(anyString(), eq(ProductInfoResponseDTO.class)))
                .thenThrow(new RestClientException("service unavailable"));

        assertThrows(ServiceUnavailableException.class, () -> orderService.createOrder(request));
    }
}
