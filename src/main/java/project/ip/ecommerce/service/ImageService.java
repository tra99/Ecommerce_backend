package project.ip.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.ip.ecommerce.entity.Image;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.ImageRepository;
import project.ip.ecommerce.repository.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    public ImageService(ImageRepository imageRepository,ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository=productRepository;
    }

    public String saveImage(MultipartFile file) {
        try {
            Image image = new Image();
            image.setImageData(file.getBytes());
            Image savedImage = imageRepository.save(image);
            return savedImage.getId();
        } catch (IOException e) {
            System.out.println("$e");
            return null;
        }
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> getImageById(String id) {
        return imageRepository.findById(id);
    }

    public void deleteImageById(String id) {
        imageRepository.deleteById(id);
    }

     public String saveImageForProduct(String productId, MultipartFile file) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                Image image = new Image();
                image.setImageData(file.getBytes());
                image.setProduct(product);
                // product.getImages().add(image);
                imageRepository.save(image);
                return image.getId();
            } else {
                return null;
            }
        } catch (IOException e) {
            System.out.println("$e");;
        }
        return productId;
    }
    public Image createOrUpdateImage(Image image) {
        String imageId = UUID.randomUUID().toString(); // Generate a unique ID
        image.setId(imageId); // Set the generated ID to the Image entity

        return imageRepository.save(image); // Save the Image entity using the repository
    }
}
