package project.ip.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.ip.ecommerce.entity.Image;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.service.ImageService;
import project.ip.ecommerce.service.ProductService;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;
    private final ProductService productService;

    public ImageController(ImageService imageService, ProductService productService) {
        this.imageService = imageService;
        this.productService = productService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("productId") String productId) {
        try {
            Image image = new Image();
            // Set other properties for the image

            Optional<Product> optionalProduct = productService.getProductById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                image.setProduct(product);
                // Set other properties for the image (like imageData, imageUrl, etc.)

                Image createdImage = imageService.createOrUpdateImage(image);
                // Handle the createdImage as needed

                return ResponseEntity.ok("Image uploaded successfully");
            } else {
                return ResponseEntity.badRequest().body("Product not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable String id) {
        return imageService.getImageById(id)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImageById(@PathVariable String id) {
        imageService.deleteImageById(id);
        return new ResponseEntity<>("Image with ID " + id + " deleted successfully.", HttpStatus.NO_CONTENT);
    }
}
