// package project.ip.ecommerce.seeder;

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
// import java.util.Optional;

// @Component
// public class ImageDataSeeder implements CommandLineRunner {

//     private final ProductRepository productRepository;
//     private final ImageRepository imageRepository;

//     @Autowired
//     public ImageDataSeeder(ProductRepository productRepository, ImageRepository imageRepository) {
//         this.productRepository = productRepository;
//         this.imageRepository = imageRepository;
//     }

//     @Override
//     @Transactional
//     public void run(String... args) {
//         try {
//             Optional<Product> optionalProduct1 = productRepository.findByName("Product1");
//             Optional<Product> optionalProduct2 = productRepository.findByName("Product2");

//             optionalProduct1.ifPresent(product1 -> seedImageData("bot12.png", product1, "Product1"));
//             optionalProduct2.ifPresent(product2 -> seedImageData("sneaker.png", product2, "Product2"));

//             optionalProduct1.ifPresentOrElse(
//                     product -> System.out.println("Image 1 saved successfully for Product1"),
//                     () -> System.err.println("Product 'Product1' does not exist.")
//             );

//             optionalProduct2.ifPresentOrElse(
//                     product -> System.out.println("Image 2 saved successfully for Product2"),
//                     () -> System.err.println("Product 'Product2' does not exist.")
//             );
//         } catch (Exception e) {
//             handleSeederException(e);
//         }
//     }

//     private void seedImageData(String imageName, Product product, String productType) {
//         try {
//             File imageFile = ResourceUtils.getFile("src\\main\\java\\project\\ip\\ecommerce\\assets\\images\\" + imageName);
//             System.out.println("Image File Path: " + imageFile.getAbsolutePath());

//             byte[] imageData = readImageFile(imageFile);

//             // Create Image entity
//             Image image = new Image(imageName, imageData, product, "image/png");
//             imageRepository.save(image);
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
//     }
// }
