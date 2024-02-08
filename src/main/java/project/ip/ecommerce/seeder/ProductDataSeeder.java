// package project.ip.ecommerce.seeder;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import project.ip.ecommerce.entity.Category;
// import project.ip.ecommerce.entity.Product;
// import project.ip.ecommerce.repository.CategoryRepository;
// import project.ip.ecommerce.repository.ProductRepository;

// @Component
// public class ProductDataSeeder implements CommandLineRunner {

//     private final ProductRepository productRepository;
//     private final CategoryRepository categoryRepository;

//     @Autowired
//     public ProductDataSeeder(ProductRepository productRepository, CategoryRepository categoryRepository) {
//         this.productRepository = productRepository;
//         this.categoryRepository = categoryRepository;
//     }

//     @Override
//     public void run(String... args) {
//         seedProductData();
//     }

//     private void seedProductData() {
//         try {
//             if (productRepository.count() == 0) {

//                 // Remove the line fetching the category by name
//                 // Category category = categoryRepository.findByCategoryName("Electronics");

//                 // Create a new category
//                 Category category = new Category();
//                 category.setCategoryId("354f28b0-7012-4e0d-b0e1-5aa2c71ac522");
//                 categoryRepository.save(category);

//                 Product product1 = new Product();
//                 product1.setName("Nike Gens");
//                 product1.setDescription("A high-end Nike");
//                 product1.setPrice(124.99);
//                 product1.setQuantity(40);
//                 product1.setCategory(category);

//                 Product product2 = new Product();
//                 product2.setName("Nike Air Force 1");
//                 product2.setDescription("Iconic basketball");
//                 product2.setPrice(79.99);
//                 product2.setQuantity(50);
//                 product2.setCategory(category);

//                 Product product3 = new Product();
//                 product3.setName("Nike Air Max series");
//                 product3.setDescription("visible Air cushioning");
//                 product3.setPrice(179.99);
//                 product3.setQuantity(20);
//                 product3.setCategory(category);

//                 Product product4 = new Product();
//                 product4.setName("Nike Blazer");
//                 product4.setDescription("high-top silhouette with a retro-inspired design");
//                 product4.setPrice(123.00);
//                 product4.setQuantity(45);
//                 product4.setCategory(category);

//                 Product product5 = new Product();
//                 product5.setName("Nike React Element");
//                 product5.setDescription("futuristic design and comfort");
//                 product5.setPrice(99.99);
//                 product5.setQuantity(70);
//                 product5.setCategory(category);

              

//                 productRepository.save(product1);
//                 productRepository.save(product2);
//                 productRepository.save(product3);
//                 productRepository.save(product4);
//                 productRepository.save(product5);
//             }
//         } catch (Exception e) {
//             System.err.println(e);
//         }
//     }
// }
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
            if (productRepository.count() == 0) {
                // Only seed data if no products exist

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
                System.out.println("Product data seeded successfully.");
            } else {
                System.out.println("Product data already exists. Skipping seeding.");
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private List<Product> generateProductsForCategory(Category category) {
        if ("Addidas".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("Samba OG Shoes", "Best Running Shoes", 99.99, 10, category),
                    createProduct("Velosamba Leather Shoes", "Blue Running Shoes", 199.00, 20, category),
                    createProduct("Y-3 Gazelle", "Black Running Shoes", 180.9, 10, category),
                    createProduct("Gazelle Bold Shoes", "Lightweight Running Shoes", 123.00, 19, category),
                    createProduct("Samba ADV Shoes", "Men's Cushioned Running Shoes", 289.99, 10, category),
                    createProduct("Lite Racer Adapt 4 Slip-On Shoes", "Running Bras", 229.00, 20, category),
                    createProduct("Ultraboost 1.0 Shoes", "Running Socks", 219.99, 10, category),
                    createProduct("Rivalry 86 Low Shoes", "Overpronation Running Shoes", 169.00, 20, category),
                    createProduct("Adizero SL", "Track And Field Shoes", 99.99, 10, category),
                    createProduct("NMD_G1 Shoes", "Neutral Running Shoes", 92.00, 20, category)
            );
        }
        if ("Nike".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("Pegasus Running Shoes", "Featuring Nike's innovative React foam technology", 239.99, 10, category),
                    createProduct("Retcon", "Designed to mimic the feeling of barefoot running", 111.99, 10, category),
                    createProduct("Air Force 1", "Originally designed for basketball legend", 168.99, 10, category),
                    createProduct("Dunk", "A skateboarding shoe known for its durability,", 1998.99, 10, category),
                    createProduct("Air Max", "Featuring a full-length Air cushioning unit", 399.99, 10, category),
                    createProduct("LeBron", "A classic basketball-inspired shoe", 299.99, 10, category),
                    createProduct("Nike Blazer", "Air cushioning units", 199.99, 10, category),
                    createProduct("Nike Dunk Low", "Nike Roshe Run shoes", 99.99, 10, category),
                    createProduct("Nike Dunk Low Retro Preminum", "Originally designed for basketball", 123.54, 10, category),
                    createProduct("Nike Air Max", "Built for running",143, 10, category)

            );
        }
        if ("Jordan".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("Jordan Pegasus Running Shoes", "Featuring Nike's innovative React foam technology", 139.99, 10, category),
                    createProduct("Jordan Retcon", "Designed to mimic the feeling of barefoot running", 211.99, 10, category),
                    createProduct("Jordan Air Force 1", "Originally designed for basketball legend", 268.99, 10, category),
                    createProduct("Jordan Dunk", "A skateboarding shoe known for its durability,", 198.99, 10, category),
                    createProduct("Jordan Air Max", "Featuring a full-length Air cushioning unit", 299.99, 10, category),
                    createProduct("Jordan LeBron", "A classic basketball-inspired shoe", 219.99, 10, category),
                    createProduct("Jordan Blazer", "Air cushioning units", 189.99, 10, category),
                    createProduct("Jordan Dunk Low", "Nike Roshe Run shoes", 239.99, 10, category),
                    createProduct("Jordan Dunk Low Retro Preminum", "Originally designed for basketball", 123.54, 10, category),
                    createProduct("Jordan Air Max", "Built for running",143, 10, category)
            );
        }
        if ("Converse".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("Converse Pegasus Running Shoes", "Featuring Nike's innovative React foam technology", 139.99, 10, category),
                    createProduct("Converse Retcon", "Designed to mimic the feeling of barefoot running", 211.99, 10, category),
                    createProduct("Converse Air Force 1", "Originally designed for basketball legend", 268.99, 10, category),
                    createProduct("Converse Dunk", "A skateboarding shoe known for its durability,", 198.99, 10, category),
                    createProduct("Converse Air Max", "Featuring a full-length Air cushioning unit", 299.99, 10, category),
                    createProduct("Converse LeBron", "A classic basketball-inspired shoe", 219.99, 10, category),
                    createProduct("Converse Blazer", "Air cushioning units", 189.99, 10, category),
                    createProduct("Converse Dunk Low", "Nike Roshe Run shoes", 239.99, 10, category),
                    createProduct("Converse Dunk Low Retro Preminum", "Originally designed for basketball", 123.54, 10, category),
                    createProduct("Converse Air Max", "Built for running",143, 10, category)
            );
        }
        if ("Vans".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("Vans Pegasus Running Shoes", "Featuring Nike's innovative React foam technology", 119.99, 10, category),
                    createProduct("Vans Retcon", "Designed to mimic the feeling of barefoot running", 111.99, 10, category),
                    createProduct("Vans Air Force 1", "Originally designed for basketball legend", 68.99, 10, category),
                    createProduct("Vans Dunk", "A skateboarding shoe known for its durability,", 98.99, 10, category),
                    createProduct("Vans Air Max", "Featuring a full-length Air cushioning unit", 99.99, 10, category),
                    createProduct("Vans LeBron", "A classic basketball-inspired shoe", 119.99, 10, category),
                    createProduct("Vans Blazer", "Air cushioning units", 89.99, 10, category),
                    createProduct("Vans Dunk Low", "Nike Roshe Run shoes", 69.99, 10, category),
                    createProduct("Vans Dunk Low Retro Preminum", "Originally designed for basketball", 123.54, 10, category),
                    createProduct("Vans Air Max", "Built for running",43, 10, category)
            );
        }
        if ("Puma".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("Puma Pegasus Running Shoes", "Featuring Nike's innovative React foam technology", 119.99, 10, category),
                    createProduct("Puma Retcon", "Designed to mimic the feeling of barefoot running", 111.99, 10, category),
                    createProduct("Puma Air Force 1", "Originally designed for basketball legend", 68.99, 10, category),
                    createProduct("Puma Dunk", "A skateboarding shoe known for its durability,", 98.99, 10, category),
                    createProduct("Puma Air Max", "Featuring a full-length Air cushioning unit", 99.99, 10, category),
                    createProduct("Puma LeBron", "A classic basketball-inspired shoe", 119.99, 10, category),
                    createProduct("Puma Blazer", "Air cushioning units", 89.99, 10, category),
                    createProduct("Puma Dunk Low", "Nike Roshe Run shoes", 69.99, 10, category),
                    createProduct("Puma Dunk Low Retro Preminum", "Originally designed for basketball", 123.54, 10, category),
                    createProduct("Puma Air Max", "Built for running",43, 10, category)
            );
        }
        
        if ("361".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("361 Pegasus Running Shoes", "Featuring Nike's innovative React foam technology", 119.99, 10, category),
                    createProduct("361 Retcon", "Designed to mimic the feeling of barefoot running", 111.99, 10, category),
                    createProduct("361 Air Force 1", "Originally designed for basketball legend", 68.99, 10, category),
                    createProduct("361 Dunk", "A skateboarding shoe known for its durability,", 98.99, 10, category),
                    createProduct("361 Air Max", "Featuring a full-length Air cushioning unit", 99.99, 10, category),
                    createProduct("361 LeBron", "A classic basketball-inspired shoe", 119.99, 10, category),
                    createProduct("361 Blazer", "Air cushioning units", 89.99, 10, category),
                    createProduct("361 Dunk Low", "Nike Roshe Run shoes", 69.99, 10, category),
                    createProduct("361 Dunk Low Retro Preminum", "Originally designed for basketball", 123.54, 10, category),
                    createProduct("361 Air Max", "Built for running",43, 10, category)
            );
        }
        if ("New Balance".equals(category.getCategoryName())) {
            return List.of(
                    createProduct("New Balance Pegasus Running Shoes", "Featuring Nike's innovative React foam technology", 219.99, 10, category),
                    createProduct("New Balance Retcon", "Designed to mimic the feeling of barefoot running", 311.99, 10, category),
                    createProduct("New Balance Air Force 1", "Originally designed for basketball legend", 368.99, 10, category),
                    createProduct("New Balance Dunk", "A skateboarding shoe known for its durability,", 398.99, 10, category),
                    createProduct("New Balance Air Max", "Featuring a full-length Air cushioning unit", 399.99, 10, category),
                    createProduct("New Balance LeBron", "A classic basketball-inspired shoe", 319.99, 10, category),
                    createProduct("New Balance Blazer", "Air cushioning units", 389.99, 10, category),
                    createProduct("New Balance Dunk Low", "Nike Roshe Run shoes", 369.99, 10, category),
                    createProduct("New Balance Dunk Low Retro Preminum", "Originally designed for basketball", 323.54, 10, category),
                    createProduct("New Balance Air Max", "Built for running",343, 10, category)
            );
        }
        
        // Add more conditions for other categories

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