// package project.ip.ecommerce.seeder;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import project.ip.ecommerce.entity.Image;
// import project.ip.ecommerce.entity.Product;
// import project.ip.ecommerce.repository.ImageRepository;
// import project.ip.ecommerce.repository.ProductRepository;

// @Component
// public class ImageDataSeeder implements CommandLineRunner {

//     private final ImageRepository imageRepository;
//     private final ProductRepository productRepository;

//     @Autowired
//     public ImageDataSeeder(ImageRepository imageRepository, ProductRepository productRepository) {
//         this.imageRepository = imageRepository;
//         this.productRepository = productRepository;
//     }

//     @Override
//     public void run(String... args) {
//         seedImageData();
//     }

//     private void seedImageData() {
//         try {
//             if (imageRepository.count() == 0) {
//                 // Retrieve a product from the database to associate the image with
//                 Product product = productRepository.findById("28a8e40e-37c1-48dc-a082-002dd79ae12e").orElse(null);
//                 if (product != null) {
//                     byte[] imageData = // Load or generate the image data

//                     Image image = new Image(imageData, "bot12.png", "image/png", product);

//                     imageRepository.save(image);
//                 }
//             }
//         } catch (Exception e) {
//             System.err.println(e);
//         }
//     }
// }