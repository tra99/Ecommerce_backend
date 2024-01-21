package project.ip.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.ip.ecommerce.entity.OrderTable;

public interface OrderRepository extends JpaRepository<OrderTable, String> {
    @Query("SELECT o FROM OrderTable o")
    List<OrderTable> findAllOrderTables();

    // Custom query to find an order table by ID
    @Query("SELECT o FROM OrderTable o WHERE o.id = :id")
    Optional<OrderTable> findOrderTableById(@Param("id") String id);
}
