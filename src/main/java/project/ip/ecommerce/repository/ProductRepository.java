package project.ip.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ip.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product,String>{
    
}
