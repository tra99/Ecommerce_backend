package project.ip.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ip.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product,String>{
    Optional<Product> findById(String id);
}
