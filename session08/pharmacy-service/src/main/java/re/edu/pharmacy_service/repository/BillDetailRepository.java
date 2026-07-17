package re.edu.pharmacy_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import re.edu.pharmacy_service.model.entity.BillDetail;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {

}
