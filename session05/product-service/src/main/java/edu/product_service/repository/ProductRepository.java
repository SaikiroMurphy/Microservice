package edu.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.product_service.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
