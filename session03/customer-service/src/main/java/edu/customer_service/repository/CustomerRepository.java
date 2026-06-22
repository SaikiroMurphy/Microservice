package edu.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.customer_service.model.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
