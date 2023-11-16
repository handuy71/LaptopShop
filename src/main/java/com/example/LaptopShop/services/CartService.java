package com.example.LaptopShop.services;

import com.example.LaptopShop.DTO.CartProductDTO;
import com.example.LaptopShop.DTO.ProductDTO;
import com.example.LaptopShop.models.CartProduct;
import com.example.LaptopShop.models.Product;
import com.example.LaptopShop.models.User;
import com.example.LaptopShop.repositories.ProductRepository;
import com.example.LaptopShop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final UserRepository userRepository; // Assuming you have a UserRepository

    private final ProductRepository productRepository; // Assuming you have a ProductRepository

    public CartService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    public void addProductToCart(User user, Long productId) {
        // Assuming you have a method in your UserRepository to get a managed entity
//        User managedUser = userRepository.findByUsername(user.getUsername());
        Product product = productRepository.getById(productId);
        // Check if the product is already in the user's cart
        boolean productAlreadyInCart = user.getCartProducts()
                .stream()
                .anyMatch(cartProduct -> cartProduct.getProduct().getId().equals(productId));

        if (productAlreadyInCart) {
            // If the product is already in the cart, you might want to update the quantity
            // For simplicity, let's assume you are updating the quantity by 1
            user.getCartProducts()
                    .stream()
                    .filter(cartProduct -> cartProduct.getProduct().getId().equals(productId))
                    .findFirst()
                    .ifPresent(cartProduct -> cartProduct.setQuantity(cartProduct.getQuantity() + 1));
        } else {
            // If the product is not in the cart, add it with a default quantity of 1
            CartProduct cartProduct = new CartProduct();
            cartProduct.setProduct(product);
            cartProduct.setQuantity(1);

            user.getCartProducts().add(cartProduct);
        }

        // Save the updated user entity to persist the changes
        userRepository.save(user);
    }

    public List<CartProductDTO> getCartProductsByUser(User user) {
        List<CartProductDTO> cartProductDTOs = new ArrayList<>();

        for (CartProduct cartProduct : user.getCartProducts()) {
            CartProductDTO cartProductDTO = new CartProductDTO();
            cartProductDTO.setId(cartProduct.getId());
            cartProductDTO.setProductId(cartProduct.getProduct().getId());
            cartProductDTO.setProductName(cartProduct.getProduct().getName());
            cartProductDTO.setProductPrice(cartProduct.getProduct().getPrice());
            cartProductDTO.setWarranty(cartProduct.getProduct().getWarranty());
            cartProductDTO.setQuantity(cartProduct.getQuantity());
            cartProductDTOs.add(cartProductDTO);
        }

        return cartProductDTOs;
    }

    public void updateCartProductQuantity(User user, Long productId, int newQuantity) {
        Product product = productRepository.getById(productId);

        // Check if the product is already in the user's cart
        Optional<CartProduct> CartProduct = user.getCartProducts()
                .stream()
                .filter(cartProduct -> cartProduct.getProduct().getId().equals(productId))
                .findFirst();
        CartProduct.get().setQuantity(newQuantity);
        // Save the updated user entity to persist the changes
        userRepository.save(user);
    }
    public void deleteCartProduct(User user, Long productId) {
        user.getCartProducts().removeIf(cartProduct ->
                cartProduct.getProduct().getId().equals(productId));
        userRepository.save(user);
    }
}

