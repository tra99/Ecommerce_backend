package project.ip.ecommerce.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.ip.ecommerce.entity.Image;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.ImageRepository;
import project.ip.ecommerce.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Component
public class ImageDataSeeder implements CommandLineRunner {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ImageDataSeeder(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        seedImageData();
    }

    private void seedImageData() {
        try {
            List<String> productIds = List.of("productId1", "productId2", "productId3");

            for (String productId : productIds) {
                Optional<Product> existingProduct = productRepository.findById(productId);

                if (existingProduct.isPresent()) {
                    // Replace with the actual path to the image file
                    Path imagePath = Paths.get("path/to/your/image.jpg");
                    byte[] imageData = Files.readAllBytes(imagePath);

                    Image image = Image.builder()
                            .name("SampleImage")
                            .type("jpg")
                            .imageData(imageData)
                            .product(existingProduct.get())
                            .build();

                    imageRepository.save(image);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
