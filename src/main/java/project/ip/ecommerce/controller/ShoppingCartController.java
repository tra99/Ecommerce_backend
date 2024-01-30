package project.ip.ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.ShoppingCart;
import project.ip.ecommerce.entity.User;
import project.ip.ecommerce.repository.ProductRepository;
import project.ip.ecommerce.repository.UserRepository;
import project.ip.ecommerce.service.ShoppingCartService;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/ShoppingCart") 
@RequiredArgsConstructor



public class ShoppingCartController {
   
    @Autowired
    private final ShoppingCartService shoppingCartService;


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // Define ShoppingCartRequest as a static inner class
    public static class ShoppingCartRequest {
        private Long userId;
        private String productId;
        private int quantity;
    
        // Getters and setters
        public Long getUserId() {
            return userId;
        }
    
        public void setUserId(Long userId) {
            this.userId = userId;
        }
    
        public String getProductId() {
            return productId;
        }
    
        public void setProductId(String productId) {
            this.productId = productId;
        }
    
        public int getQuantity() {
            return quantity;
        }
    
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    @PostMapping
    public ResponseEntity<?> createShoppingCart(@RequestBody ShoppingCartRequest request) {
        try {
            // Fetch user and product details from the request
            User user = userRepository.findById(request.getUserId()).orElse(null);
            Product product = productRepository.findById(request.getProductId()).orElse(null);
            
            // Check if user and product are not null before proceeding
            if (user != null && product != null) {
                // Call addToCart method with user, product, and quantity
                shoppingCartService.addToCart(user, product, request.getQuantity());
                return ResponseEntity.ok().build();
            } else {
                // User or product not found, return appropriate response
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating shopping cart");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ShoppingCart>> getShoppingCartByUser(@PathVariable Long userId) {
        try {
            // Find user by ID
            User user = userRepository.findById(userId).orElse(null);

            // Check if user exists
            if (user != null) {
                // Fetch shopping cart items for the user
                List<ShoppingCart> shoppingCartItems = shoppingCartService.getShoppingCartByUser(user);
                
                // Return the shopping cart items in the response
                return ResponseEntity.ok().body(shoppingCartItems);
            } else {
                // User not found, return appropriate response
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<?> deleteShoppingCartItem(@PathVariable Long cartItemId) {
        try {
            // Find the shopping cart item by ID
            ShoppingCart cartItem = shoppingCartService.getCartItemById(cartItemId);

            // Check if the shopping cart item exists
            if (cartItem != null) {
                // Delete the shopping cart item
                shoppingCartService.removeCartItem(cartItemId);
                return ResponseEntity.ok().build();
            } else {
                // Shopping cart item not found, return appropriate response
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting shopping cart item");
        }
    }
    @PatchMapping("/{cartItemId}")
    public ResponseEntity<?> updateShoppingCartItemQuantity(@PathVariable Long cartItemId, @RequestBody ShoppingCartRequest request) {
        try {
            // Fetch the shopping cart item by ID
            ShoppingCart cartItem = shoppingCartService.getCartItemById(cartItemId);

            // Check if the shopping cart item exists
            if (cartItem != null) {
                // Update the quantity of the shopping cart item
                cartItem.setQuantity(request.getQuantity());
                shoppingCartService.updateCartItemQuantity(cartItemId, request.getQuantity());
                return ResponseEntity.ok().build();
            } else {
                // Shopping cart item not found, return appropriate response
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating shopping cart item quantity");
        }
    }
}


