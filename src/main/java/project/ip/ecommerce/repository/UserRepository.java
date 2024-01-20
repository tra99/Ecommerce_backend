package project.ip.ecommerce.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.ip.ecommerce.entity.Role;
import project.ip.ecommerce.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
    boolean existsByEmail(String email);
}

