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
// package project.ip.ecommerce.seeder;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import project.ip.ecommerce.entity.Category;
// import project.ip.ecommerce.entity.Image;
// import project.ip.ecommerce.entity.Product;
// import project.ip.ecommerce.repository.CategoryRepository;
// import project.ip.ecommerce.repository.ImageRepository;
// import project.ip.ecommerce.repository.ProductRepository;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.util.ResourceUtils;
// import org.springframework.util.StreamUtils;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;

// @Component
// public class ProductDataSeeder implements CommandLineRunner {

//     private final ProductRepository productRepository;
//     private final CategoryRepository categoryRepository;
//     private final ImageRepository imageRepository;

//     @Autowired
//     public ProductDataSeeder(ProductRepository productRepository, CategoryRepository categoryRepository, ImageRepository imageRepository) {
//         this.productRepository = productRepository;
//         this.categoryRepository = categoryRepository;
//         this.imageRepository = imageRepository;
//     }

//     @Override
//     @Transactional
//     public void run(String... args) {
//         seedProductData();
//         seedImageData();
//     }

//     private void seedProductData() {
//         try {
//             if (productRepository.count() == 0) {

//                 Category category = categoryRepository.findByCategoryName("Electronics");
//                 if (category == null) {
//                     category = new Category();
//                     category.setCategoryId("8c9a555a-ea4d-41a9-a4ca-06b905134a43");
//                     categoryRepository.save(category);
//                 }

//                 Product product1 = new Product();
//                 product1.setName("Smartphone");
//                 product1.setDescription("A high-end smartphone");
//                 product1.setPrice(799.99);
//                 product1.setQuantity(50);
//                 product1.setCategory(category);

//                 Product product2 = new Product();
//                 product2.setName("Laptop");
//                 product2.setDescription("A powerful laptop");
//                 product2.setPrice(1299.99);
//                 product2.setQuantity(30);
//                 product2.setCategory(category);

//                 productRepository.save(product1);
//                 productRepository.save(product2);
//             }
//         } catch (Exception e) {
//             System.err.println(e);
//         }
//     }

//     private void seedImageData() {
//         try {
//             if (imageRepository.count() == 0) {
//                 Product product1 = productRepository.findByName("Smartphone").orElse(createDefaultProduct("Smartphone"));
//                 Product product2 = productRepository.findByName("Jodan").orElse(createDefaultProduct("Jodan"));
                

//                 // Seed images for product1
//                 seedImage(product1, "smartphone_image1.png");

//                 // Seed images for product2
//                 seedImage(product2, "Jodan.png");
//             }
//         } catch (Exception e) {
//             System.err.println(e);
//         }
//     }

//     private Product createDefaultProduct(String name) {
//         // Create and return a default product with the specified name
//         // You can customize this based on your requirements
//         Product defaultProduct = new Product();
//         defaultProduct.setName(name);
//         defaultProduct.setDescription("Default description");
//         defaultProduct.setPrice(0.0);
//         defaultProduct.setQuantity(0);
//         return defaultProduct;
//     }

//     private void seedImage(Product product, String imageName) throws IOException {
//         // Replace with the path to your sample image file
//         File imageFile = ResourceUtils.getFile("src\\main\\java\\project\\ip\\ecommerce\\assets\\images\\" + imageName);
//         byte[] imageData = readImageFile(imageFile);

//         // Create Image entity
//         Image image = new Image();
//         image.setImageData(imageData);
//         image.setName(imageName);
//         image.setType("image/png");
//         image.setProduct(product);

//         // Save to the database
//         imageRepository.save(image);
//     }

//     private byte[] readImageFile(File file) throws IOException {
//         try (FileInputStream inputStream = new FileInputStream(file)) {
//             return StreamUtils.copyToByteArray(inputStream);
//         }
//     }
// }

