package session02.session02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import session02.session02.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
