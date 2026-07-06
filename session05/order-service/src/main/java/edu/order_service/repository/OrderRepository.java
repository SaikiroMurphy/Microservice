package edu.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.order_service.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
