package project.ip.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import project.ip.ecommerce.service.ImageService;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService service;

    @PostMapping
    public ResponseEntity<Object> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("product_id") String productId) throws IOException {
        try {
            String uploadImage = service.uploadImage(file, productId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(uploadImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Object> downloadImage(@PathVariable String fileName) {
        try {
            byte[] imageData = service.downloadImage(fileName);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(imageData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Image not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteImageById(@PathVariable long id){
        try {
            service.deleteImageById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Image not found for id " + id);
        }
    }
}
