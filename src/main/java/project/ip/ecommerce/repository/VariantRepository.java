package project.ip.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ip.ecommerce.entity.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, String> {
    List<Variant> findByColor(String color);
}

