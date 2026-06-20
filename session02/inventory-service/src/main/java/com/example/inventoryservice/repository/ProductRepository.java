package com.example.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventoryservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
