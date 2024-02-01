package project.ip.ecommerce.repository;

import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ip.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,String>{
    Optional<Product> findById(String id);

    // search
    List<Product> findByNameContainingIgnoreCase(String name);

}
