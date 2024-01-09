package project.ip.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Category {
    @Id
    private String id;
    private String categoryName;

    public Category() {
        this.id = UUID.randomUUID().toString();
    }

    public String getCategoryId() {
        return id;
    }

    public void setCategoryId(String categoryId) {
        this.id = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
