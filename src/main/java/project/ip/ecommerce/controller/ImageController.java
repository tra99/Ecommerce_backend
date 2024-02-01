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

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadImage(
        @RequestParam("image") MultipartFile file,
        @RequestParam("product_id") String productId,
        @RequestHeader(value = "X-Chunk-Index", defaultValue = "0") int chunkIndex,
        @RequestHeader(value = "X-Total-Chunks", defaultValue = "1") int totalChunks,
        @RequestParam(value = "fileName", defaultValue = "") String fileName) throws IOException {
        try {
            if (chunkIndex == 0) {
                // First chunk, initialize the upload

                // Ensure fileName is not null or empty before initiating upload
                if (fileName == null || fileName.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("File name cannot be null or empty");
                }

                service.initiateUpload(file, productId, fileName);
            }

            // Process the chunk
            service.processChunk(file.getBytes(), productId, chunkIndex);

            if (chunkIndex == totalChunks - 1) {
                // Last chunk, finalize the upload
                String uploadImage = service.finalizeUpload(productId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(uploadImage);
            }

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
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
    public ResponseEntity<Object> deleteImageById(@PathVariable long id) {
        try {
            service.deleteImageById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Image not found for id " + id);
        }
    }
}


    // @PostMapping
    // public ResponseEntity<Object> uploadImage(
    //         @RequestParam("image") MultipartFile file,
    //         @RequestParam("product_id") String productId) throws IOException {
    //     try {
    //         String uploadImage = service.uploadImage(file, productId);
    //         return ResponseEntity.status(HttpStatus.OK)
    //                 .body(uploadImage);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                 .body("Failed to upload image: " + e.getMessage());
    //     }
    // }

    // private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    // @PostMapping
    // public ResponseEntity<String> uploadImage(
    //         @RequestParam("image") MultipartFile file,
    //         @RequestParam("product_id") String productId) {
    //     try {
    //         // Asynchronously process the image
    //         CompletableFuture.runAsync(() -> processImageAsync(file));

    //         return ResponseEntity.status(HttpStatus.ACCEPTED)
    //                 .body("Image upload request accepted for processing.");
    //     } catch (Exception e) {
    //         logger.error("Failed to process image upload request", e);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Failed to process image upload request. Reason: " + e.getMessage());
    //     }
    // }
    // @Async
    // public void processImageAsync(MultipartFile file) {
    //     try {
    //         // Stream the file content to the service for processing
    //         service.processImage(file.getInputStream());
    //     } catch (IOException e) {
    //         logger.error("Failed to process image asynchronously", e);
    //     }
    // }