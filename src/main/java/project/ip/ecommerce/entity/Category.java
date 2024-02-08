package project.ip.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Category {
    @Id
    @Column(name = "category_id") // Adjust the column name if needed
    private String categoryId;

    @Column(name = "category_name") // Adjust the column name if needed
    private String categoryName;

    public Category() {
        this.categoryId = UUID.randomUUID().toString();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
