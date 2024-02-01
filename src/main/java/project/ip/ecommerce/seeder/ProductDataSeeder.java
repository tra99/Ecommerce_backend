package project.ip.ecommerce.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.ip.ecommerce.entity.Category;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.CategoryRepository;
import project.ip.ecommerce.repository.ProductRepository;


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
            if (productRepository.count() == 6) {

                Category category = categoryRepository.findByCategoryName("Electronics");
                if (category == null) {
                    category = new Category();
                    category.setCategoryId("8c9a555a-ea4d-41a9-a4ca-06b905134a43");
                    categoryRepository.save(category);
                }

                Product product1 = new Product();
                product1.setName("Smartphone");
                product1.setDescription("A high-end smartphone");
                product1.setPrice(799.99);
                product1.setQuantity(50);
                product1.setCategory(category);

                Product product2 = new Product();
                product2.setName("Smartphone");
                product2.setDescription("A high-end smartphone");
                product2.setPrice(799.99);
                product2.setQuantity(50);
                product2.setCategory(category);

                productRepository.save(product1);
                productRepository.save(product2);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
