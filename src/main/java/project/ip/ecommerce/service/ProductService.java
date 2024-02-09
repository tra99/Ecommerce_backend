package project.ip.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> searchProductsByName(String name){
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryCategoryName(categoryName);
    }

    public Page<Product> getProductsByCategoryName(String categoryName, Pageable pageable) {
        return productRepository.findByCategoryCategoryName(categoryName, pageable);
    }
}