package session02.session02.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import session02.session02.model.entity.Order;
import session02.session02.repository.OrderRepository;
import session02.session02.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	public Order getOrderById(Integer id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
	}
}
