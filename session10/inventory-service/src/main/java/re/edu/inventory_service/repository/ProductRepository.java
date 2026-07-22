package re.edu.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import re.edu.inventory_service.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    @Modifying
    @Transactional
    @Query("""
            UPDATE Product p
            SET p.stock = p.stock - :quantity
            WHERE p.id = :productId
            """)
    int decreaseStock(Long productId, Integer quantity);
}
