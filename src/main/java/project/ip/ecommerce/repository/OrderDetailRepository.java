package project.ip.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ip.ecommerce.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
