package project.ip.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.ip.ecommerce.entity.Image;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.ImageRepository;
import project.ip.ecommerce.repository.ProductRepository;
import project.ip.ecommerce.util.ImageUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private ProductRepository productRepository;

    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB chunk size

    private final Map<String, ByteArrayOutputStream> uploadBuffers = new ConcurrentHashMap<>();
    
    // ConcurrentHashMap to store file names associated with each product ID
    private final Map<String, String> uploadFileNames = new ConcurrentHashMap<>();

    public void initiateUpload(MultipartFile file, String productId, String fileName) {
        // Create a new entry in the uploadBuffers map for the product ID
        uploadBuffers.put(productId, new ByteArrayOutputStream());
        
        // Save the provided fileName for later use
        if (fileName != null && !fileName.isEmpty()) {
            uploadFileNames.put(productId, fileName);
        } else {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
    }

    public void processChunk(byte[] bytes, String productId, int chunkIndex) {
        ByteArrayOutputStream buffer = uploadBuffers.get(productId);

        if (buffer != null) {
            try {
                buffer.write(bytes);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        } else {
            throw new IllegalStateException("Upload buffer not found for product ID: " + productId);
        }
    }

    public String finalizeUpload(String productId) {
        try {
            ByteArrayOutputStream buffer = uploadBuffers.remove(productId);
            String fileName = uploadFileNames.remove(productId); // Retrieve the saved fileName
    
            if (buffer != null && fileName != null) {
                byte[] imageData = buffer.toByteArray();
    
                Optional<Product> optionalProduct = productRepository.findById(productId);
    
                if (optionalProduct.isPresent()) {
                    Product product = optionalProduct.get();
                    Image image = repository.save(Image.builder()
                            .name(fileName) // Use the saved fileName
                            .type("image/png") // Set the appropriate content type
                            .imageData(ImageUtils.compressImage(imageData))
                            .product(product)
                            .build()
                    );
    
                    if (image != null) {
                        return "Upload completed successfully for product ID: " + productId;
                    } else {
                        throw new IllegalStateException("Failed to save image for product ID: " + productId);
                    }
                } else {
                    throw new IllegalStateException("Product not found for ID: " + productId);
                }
            } else {
                throw new IllegalStateException("Upload buffer or fileName not found for product ID: " + productId);
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            return "Failed to finalize upload for product ID: " + productId + ". Reason: " + e.getMessage();
        }
    }
    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = repository.findByName(fileName);
    
        if (dbImageData.isPresent()) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        }
    
        return null;
    }

    public List<Image> getAllImages() {
        return repository.findAll();
    }

    public Optional<Image> getImageById(Long id) {
        return repository.findById(id);
    }

    public Image createOrUpdate(Image image) {
        return repository.save(image);
    }

    public void deleteImageById(long id) {
        repository.deleteById(id);
    }
}