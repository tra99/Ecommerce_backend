package project.ip.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.ip.ecommerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    
    Category findByCategoryName(String categoryName);

    @Query("SELECT c.id FROM Category c")
    List<String> findAllCategoryIds();
    
}
