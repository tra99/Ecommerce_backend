package project.ip.ecommerce.service;

import org.springframework.stereotype.Service;

import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }
}

