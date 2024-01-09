package project.ip.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.ip.ecommerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    
    Category findByCategoryName(String categoryName);
    
}
