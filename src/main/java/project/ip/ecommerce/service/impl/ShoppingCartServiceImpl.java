package project.ip.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ip.ecommerce.entity.Product;
import project.ip.ecommerce.entity.ShoppingCart;
import project.ip.ecommerce.entity.User;
import project.ip.ecommerce.repository.ProductRepository;
import project.ip.ecommerce.repository.ShoppingCartRepository;
import project.ip.ecommerce.repository.UserRepository;
import project.ip.ecommerce.service.ShoppingCartService;

import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCart> getShoppingCartByUser(User user) {
        return shoppingCartRepository.findByUser(user);
    }

    @Override
    public void addToCart(User user, Product product, int quantity) {
        ShoppingCart cartItem = new ShoppingCart();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        shoppingCartRepository.save(cartItem);
    }

    @Override
    public List<ShoppingCart> getCartItemsByUser(User user) {
        return shoppingCartRepository.findByUser(user);
    }

    @Override
    public ShoppingCart getCartItemById(Long cartItemId) {
        return shoppingCartRepository.findById(cartItemId).orElse(null);
    }

    @Override
    public void updateCartItemQuantity(Long cartItemId, int newQuantity) {
        ShoppingCart cartItem = shoppingCartRepository.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(newQuantity);
            shoppingCartRepository.save(cartItem);
        }
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        shoppingCartRepository.deleteById(cartItemId);
    }
}
