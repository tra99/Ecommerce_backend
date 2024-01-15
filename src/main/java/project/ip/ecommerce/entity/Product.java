package project.ip.ecommerce.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Variant> variants = new HashSet<>();

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
    public Set<Variant> getVariants() {
        return variants;
    }

    public void setVariants(Set<Variant> variants) {
        this.variants = variants;
    }

    public void addVariant(Variant variant) {
        variant.setProduct(this);
        variants.add(variant);
    }

    public void removeVariant(Variant variant) {
        variants.remove(variant);
        variant.setProduct(null);
    }
       // Fix relation between product to image, product have not key to make relation with image 

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
    public void addImage(Image image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        image.setProduct(this);
        images.add(image);
    }

    public void removeImage(Image image) {
        if (images != null) {
            images.remove(image);
            image.setProduct(null);
        }
    }
}
 