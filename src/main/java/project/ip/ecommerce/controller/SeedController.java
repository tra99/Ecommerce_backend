// package project.ip.ecommerce.controller;
// import org.apache.el.stream.Optional;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import project.ip.ecommerce.entity.Category;
// import project.ip.ecommerce.entity.Product;
// import project.ip.ecommerce.service.ProductService;

// @RestController
// @CrossOrigin(origins = "*")
// @RequestMapping("/api/seed")
// public class SeedController {

//     private final ProductService productService;

//     public SeedController(ProductService productService) {
//         this.productService = productService;
//     }

//     @PostMapping("/products")
//     public ResponseEntity<String> seedProducts() {
//         try {
//             // Seed 10 products
//             for (int i = 0; i < 10; i++) {
//                 Product product = new Product();
//                 product.setName("Product " + i);
//             product.setDescription("Description of Product " + i);
//             product.setPrice(19.99 + i); // Example price
//             product.setQuantity(100); // Example quantity
//             product.setCategory(category.get());

//                 productService.createOrUpdateProduct(product);
//             }
//             return ResponseEntity.ok("Products seeded successfully");
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                     .body("Error seeding products: " + e.getMessage());
//         }
//     }
// }
