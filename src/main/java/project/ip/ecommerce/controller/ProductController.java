package project.ip.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ip.ecommerce.entity.Category;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.ProductRequest;
import project.ip.ecommerce.entity.Variant;
import project.ip.ecommerce.service.CategoryService;
import project.ip.ecommerce.service.ProductService;
import project.ip.ecommerce.service.VariantService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final VariantService variantService;

    public ProductController(ProductService productService,CategoryService categoryService,VariantService variantService) {
        this.productService = productService;
        this.categoryService=categoryService;
        this.variantService=variantService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        String categoryId = productRequest.getCategory().getId();

        Optional<Category> existingCategory = categoryService.getCategoryById(categoryId);

        if (existingCategory.isPresent()) {
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
            product.setCategory(existingCategory.get());

            Product createdProduct = productService.createOrUpdateProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        Product updatedProduct = productService.createOrUpdateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
        String message = "Product with ID " + id + " deleted successfully.";
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

    // Variant for product
    @PostMapping("/product/{productId}/viriants/create")
    public ResponseEntity<Variant> createVariantForProduct(@PathVariable String productId, @RequestBody Variant variant) {
        Optional<Product> existingProduct = productService.getProductById(productId);

        if (existingProduct.isPresent()) {
            variant.setProduct(existingProduct.get());
            variant.setId(UUID.randomUUID().toString());
            Variant createdVariant = variantService.createOrUpdateVariant(variant);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVariant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
