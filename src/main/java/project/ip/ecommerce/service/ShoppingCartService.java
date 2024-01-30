package project.ip.ecommerce.service;

import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.ShoppingCart;
import project.ip.ecommerce.entity.User;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getShoppingCartByUser(User user);

    void addToCart(User user, Product product, int quantity);

    List<ShoppingCart> getCartItemsByUser(User user);

    ShoppingCart getCartItemById(Long cartItemId);

    void updateCartItemQuantity(Long cartItemId, int newQuantity);

    void removeCartItem(Long cartItemId);
}
