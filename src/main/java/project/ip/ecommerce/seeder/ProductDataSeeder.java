package project.ip.ecommerce.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.ip.ecommerce.entity.Category;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.Variant;
import project.ip.ecommerce.repository.CategoryRepository;
import project.ip.ecommerce.repository.ProductRepository;

import java.util.HashSet;
import java.util.Set;

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
            if (productRepository.count() == 1) {
                // Only seed data if the repository is empty

                // Get or create a category (you can adjust this based on your actual category setup)
                Category electronicsCategory = categoryRepository.findByCategoryName("Electronics");
                if (electronicsCategory == null) {
                    electronicsCategory = new Category();
                    electronicsCategory.setCategoryName("Electronics");
                    categoryRepository.save(electronicsCategory);
                }

                // Create a product and add variants and images
                Product product1 = new Product();
                product1.setName("Smartphone");
                product1.setDescription("A high-end smartphone");
                product1.setPrice(799.99);
                product1.setQuantity(50);
                product1.setCategory(electronicsCategory);

                // Add variants
                Variant variant1 = new Variant("Color", "Black");
                Variant variant2 = new Variant("Color", "White");
                product1.addVariant(variant1);
                product1.addVariant(variant2);

                // Add images
                // product1.addImage(new Image("smartphone_image_1.jpg"));
                // product1.addImage(new Image("smartphone_image_2.jpg"));

                productRepository.save(product1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
