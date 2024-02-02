// // package project.ip.ecommerce.seeder;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.CommandLineRunner;
// // import org.springframework.stereotype.Component;
// // import org.springframework.util.ResourceUtils;
// // import project.ip.ecommerce.entity.Image;
// // import project.ip.ecommerce.entity.Product;
// // import project.ip.ecommerce.repository.ImageRepository;
// // import project.ip.ecommerce.repository.ProductRepository;
// // import org.springframework.transaction.annotation.Transactional;
// // import org.springframework.util.StreamUtils;

// // import java.io.File;
// // import java.io.FileInputStream;
// // import java.io.IOException;
// // import java.util.Optional;

// // @Component
// // public class ImageDataSeeder implements CommandLineRunner {

// //     @Autowired
// //     private ProductRepository productRepository;

// //     @Autowired
// //     private ImageRepository imageRepository;

// //     @Override
// //     @Transactional
// //     public void run(String... args) throws Exception {
// //         // Check if data already exists
// //         if (imageRepository.count() == 7) {
// //             // Load sample images
// //             Optional<Product> optionalProduct = productRepository.findById("0c906829-7214-4e4f-9944-6e908da23d36");

// //             if (optionalProduct.isPresent()) {
// //                 Product product = optionalProduct.get();
// //                 // Replace with the path to your sample image file
// //                 File imageFile = ResourceUtils.getFile("src\\main\\java\\project\\ip\\ecommerce\\assets\\images\\sneaker.png");
// //                 byte[] imageData = readImageFile(imageFile);

// //                 // Create Image entity
// //                 Image image = new Image();
// //                 image.setImageData(imageData);
// //                 image.setName("image2.png");
// //                 image.setType("image/png");
// //                 image.setProduct(product); 

// //                 // Save to the database
// //                 imageRepository.save(image);
// //             }
// //         }
// //     }

// //     private byte[] readImageFile(File file) throws IOException {
// //         try (FileInputStream inputStream = new FileInputStream(file)) {
// //             return StreamUtils.copyToByteArray(inputStream);
// //         }
// //     }
// // }

// package project.ip.ecommerce.seeder;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import org.springframework.util.ResourceUtils;
// import project.ip.ecommerce.entity.Image;
// import project.ip.ecommerce.entity.Product;
// import project.ip.ecommerce.repository.ImageRepository;
// import project.ip.ecommerce.repository.ProductRepository;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.util.StreamUtils;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.util.List;

// @Component
// public class ImageDataSeeder implements CommandLineRunner {

//     @Autowired
//     private ProductRepository productRepository;

//     @Autowired
//     private ImageRepository imageRepository;

//     @Override
//     @Transactional
//     public void run(String... args) throws Exception {
//         // Check if data already exists
//         if (imageRepository.count() == 0) {
//             // Load sample images
//             List<Product> products = productRepository.findAll();

//             if (!products.isEmpty()) {
//                 Product product1 = products.get(0);
//                 Product product2 = products.size() > 1 ? products.get(1) : product1;

//                 // Replace with the path to your sample image files
//                 File imageFile1 = ResourceUtils.getFile("src\\main\\java\\project\\ip\\ecommerce\\assets\\images\\sneaker1.png");
//                 File imageFile2 = ResourceUtils.getFile("src\\main\\java\\project\\ip\\ecommerce\\assets\\images\\sneaker2.png");

//                 // Create Image entities
//                 Image image1 = createImage("image1.png", imageFile1, product1);
//                 Image image2 = createImage("image2.png", imageFile2, product2);

//                 // Save to the database
//                 imageRepository.saveAll(List.of(image1, image2));
//             }
//         }
//     }

//     private Image createImage(String imageName, File imageFile, Product product) throws IOException {
//         byte[] imageData = readImageFile(imageFile);

//         // Create Image entity
//         Image image = new Image();
//         image.setImageData(imageData);
//         image.setName(imageName);
//         image.setType("image/png");
//         image.setProduct(product);

//         return image;
//     }

//     private byte[] readImageFile(File file) throws IOException {
//         try (FileInputStream inputStream = new FileInputStream(file)) {
//             return StreamUtils.copyToByteArray(inputStream);
//         }
//     }
// }
package project.ip.ecommerce.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import project.ip.ecommerce.entity.Image;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.ImageRepository;
import project.ip.ecommerce.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import java.util.Optional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ImageDataSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (imageRepository.count() == 8) {
            // Set product_id manually for each image
            String productId1 = "d02348e6-bbb1-44c5-9de1-e9d880cb65e5"; // Replace with the actual product ID
            String productId2 = "f8f95ac7-dce7-4aa8-8682-27838292bc58"; // Replace with the actual product ID

            // Replace with the path to your sample image files
            File imageFile1 = ResourceUtils.getFile("src\\main\\java\\project\\ip\\ecommerce\\assets\\images\\sneaker.png");
            File imageFile2 = ResourceUtils.getFile("src\\main\\java\\project\\ip\\ecommerce\\assets\\images\\bot12.png");

            // Create Image entities with manually set product IDs
            Image image1 = createImage("image3.png", imageFile1, productId1,"image/png");
            Image image2 = createImage("image4.png", imageFile2, productId2,"image/png");

            // Save to the database
            imageRepository.saveAll(List.of(image1, image2));
        }
    }

    private Image createImage(String imageName, File imageFile, String productId, String type) {
        try {
            byte[] imageData = readImageFile(imageFile);
    
            // Retrieve the Product instance by ID
            Optional<Product> optionalProduct = productRepository.findById(productId);
    
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
    
                // Create Image entity
                Image image = new Image(imageName, imageData, product,type);
    
                return image;
            } else {
                throw new IllegalArgumentException("Product not found for ID: " + productId);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            throw new RuntimeException("Error creating image: " + e.getMessage());
        }
    }
    
    

    private byte[] readImageFile(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return StreamUtils.copyToByteArray(inputStream);
        }
    }
}
