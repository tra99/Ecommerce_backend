package project.ip.ecommerce.repository;

import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ip.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,String>{
    Optional<Product> findById(String id);

    // search
    List<Product> findByNameContainingIgnoreCase(String name);

    Optional<Product> findByName(String name);

    @Query("SELECT p.id FROM Product p")
    List<String> findAllProductIds();

    // List<Product> findByName(String name);
    List<Product> findAllByName(String name);

}