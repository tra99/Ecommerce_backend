package project.ip.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.ip.ecommerce.entity.Category;
import java.util.*;;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    
    Category findByCategoryName(String categoryName);
    Category findByCategoryId(String categoryId);
    
    @Query("SELECT c.id FROM Category c")
    List<String> findAllCategoryIds();
}
