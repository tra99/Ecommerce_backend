package project.ip.ecommerce.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.ip.ecommerce.entity.Category;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.CategoryRepository;
import project.ip.ecommerce.repository.ProductRepository;

import java.util.Collections;
import java.util.List;

@Component
public class ProductDataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductDataSeeder(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        seedProductData();
    }

    private void seedProductData() {
        try {
            List<String> categoryIds = categoryRepository.findAllCategoryIds();

            for (String categoryId : categoryIds) {
                Category category = categoryRepository.findById(categoryId).orElse(null);

                if (category == null) {
                    System.err.println("Category with ID " + categoryId + " does not exist.");
                    continue; 
                }

                List<Product> productsForCategory = generateProductsForCategory(category);
                productRepository.saveAll(productsForCategory);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private List<Product> generateProductsForCategory(Category category) {
        if ("Addidas".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("Product1", "Description1", 99.99, 10, category),
                    createProduct("Product2", "Description2", 149.99, 20, category)
            );
        }
        if ("Nike".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("Product1", "Description1", 99.99, 10, category),
                    createProduct("Product2", "Description2", 149.99, 20, category)
            );
        }
        // other more product
        
        else {
            return Collections.emptyList();
        }
    }
    
    private Product createProduct(String name, String description, double price, int quantity, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);
        return product;
    }
}
