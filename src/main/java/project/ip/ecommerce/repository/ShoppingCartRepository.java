package project.ip.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.ip.ecommerce.entity.ShoppingCart;
import project.ip.ecommerce.entity.User;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByUser(User user);
}