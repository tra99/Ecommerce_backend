package project.ip.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private float price;
    private int quantity;

    public Product() {
        this.id = UUID.randomUUID().toString(); 
    }
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(double d) {
        this.price = (float) d;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Fix relation between product to image, product have not key to make relation with image 

    // @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinTable(name = "image",cascade=CascadeType.ALL)
    // private List<Image> images;

    // public List<Image> getImages() {
    //     return images;
    // }

    // public void setImages(List<Image> images) {
    //     this.images = images;
    // }
}
