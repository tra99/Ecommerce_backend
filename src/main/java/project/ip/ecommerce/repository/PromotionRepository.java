package project.ip.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.ip.ecommerce.entity.Promotion;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, String> {
    Optional<Promotion> findByName(String name);
}
