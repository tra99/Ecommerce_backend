// package project.ip.ecommerce.seeder;


// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import project.ip.ecommerce.entity.Image;
// import project.ip.ecommerce.entity.Product;
// import project.ip.ecommerce.repository.ImageRepository;
// import project.ip.ecommerce.repository.ProductRepository;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.util.ResourceUtils;
// import org.springframework.util.StreamUtils;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.util.List;

// @Component
// public class ImageDataSeeder implements CommandLineRunner {

//     private final ProductRepository productRepository;
//     private final ImageRepository imageRepository;

//     private static final Logger logger = LoggerFactory.getLogger(ImageDataSeeder.class);

//     @Autowired
//     public ImageDataSeeder(ProductRepository productRepository, ImageRepository imageRepository) {
//         this.productRepository = productRepository;
//         this.imageRepository = imageRepository;
//     }

//     @Override
//     @Transactional
//     public void run(String... args) {
//         try {
//             seedImageData("bot12.png", "Product1");
//             seedImageData("sneaker.png", "Product2");
//         } catch (Exception e) {
//             handleSeederException(e);
//         }
//     }
//     private void seedImageData(String imageName, String productName) {
//         try {
//             List<Product> products = productRepository.findAllByName(productName);

//             if (products.isEmpty()) {
//                 logger.error("Product with name {} does not exist.", productName);
//                 return;
//             }

//             // Assuming you want to handle the case where multiple products have the same name
//             for (Product product : products) {
//                 // Check if images already exist for the product
//                 if (imageRepository.countImagesByProductId(product.getId()) > 0) {
//                     logger.info("Images already exist for product: {}", productName);
//                     continue; // Skip seeding for this product
//                 }
                
//                 File imageFile = ResourceUtils.getFile("src\\main\\java\\project\\ip\\ecommerce\\assets\\images\\" + imageName);
//                 System.out.println("Image File Path: " + imageFile.getAbsolutePath());

//                 byte[] imageData = readImageFile(imageFile);
//                 System.out.println("Image Data Length: " + imageData.length);

//                 // Create Image entity
//                 Image image = new Image(imageName, imageData, product, "image/png");
//                 imageRepository.save(image);
//                 logger.info("Image saved successfully. Image ID: {}", image.getId());
//             }
//         } catch (IOException e) {
//             handleSeederException(new RuntimeException("Error creating image " + imageName + ": " + e.getMessage()));
//         }
//     }

//     private byte[] readImageFile(File file) throws IOException {
//         try (FileInputStream inputStream = new FileInputStream(file)) {
//             return StreamUtils.copyToByteArray(inputStream);
//         }
//     }

//     private void handleSeederException(Exception e) {
//         e.printStackTrace();
//         System.err.println("Error in ImageDataSeeder: " + e.getMessage());
//         logger.error("Error in ImageDataSeeder: {}", e.getMessage());
//     }
// }
