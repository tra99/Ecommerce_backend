package project.ip.ecommerce.service;

import java.io.IOException;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.ip.ecommerce.entity.Image;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.ImageRepository;
import project.ip.ecommerce.repository.ProductRepository;
import project.ip.ecommerce.util.ImageUtils;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;


    @Autowired
    private ProductRepository productRepository;

    public String uploadImage(MultipartFile file, String productId) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Image image = repository.save(Image.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .product(product)
                    .build()
            );

            if (image != null) {
                return "File uploaded successfully: " + file.getOriginalFilename();
            }
        }

        return "Failed to upload file. Product not found or invalid product_id.";
    }
    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = repository.findByName(fileName);
        if (dbImageData.isPresent()) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        }
        return null;
    }

    public List<Image>getAllImages(){
        return repository.findAll();
    }

    public Optional<Image>getImageById(Long id){
        return repository.findById(id);
    }

    public Image createOrUpdate(Image image){
        return repository.save(image);
    }

    public void deleteImageById(long id){
        repository.deleteById(id);
    }
}
