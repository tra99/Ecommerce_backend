package project.ip.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, String> {
    List<Variant> findByColor(String color);

    @Query("SELECT v FROM Variant v WHERE v.product = ?1")
    List<Variant> findByProduct(Product product);

    Optional<Variant> findByNameAndProduct(String name, Product product);
}