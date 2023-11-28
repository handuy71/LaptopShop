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

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public CartService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    public void addProductToCart(User user, Long productId) {
        Product product = productRepository.getById(productId);
        boolean productAlreadyInCart = user.getCartProducts()
                .stream()
                .anyMatch(cartProduct -> cartProduct.getProduct().getId().equals(productId));

        if (productAlreadyInCart) {
            user.getCartProducts()
                    .stream()
                    .filter(cartProduct -> cartProduct.getProduct().getId().equals(productId))
                    .findFirst()
                    .ifPresent(cartProduct -> cartProduct.setQuantity(cartProduct.getQuantity() + 1));
        } else {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setProduct(product);
            cartProduct.setQuantity(1);

            user.getCartProducts().add(cartProduct);
        }

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
            cartProductDTO.setGifts(cartProduct.getProduct().getGiftNames());
            cartProductDTOs.add(cartProductDTO);
        }

        return cartProductDTOs;
    }

    public void updateCartProductQuantity(User user, Long productId, int newQuantity) {
        Product product = productRepository.getById(productId);
        Optional<CartProduct> CartProduct = user.getCartProducts()
                .stream()
                .filter(cartProduct -> cartProduct.getProduct().getId().equals(productId))
                .findFirst();
        CartProduct.get().setQuantity(newQuantity);
        userRepository.save(user);
    }
    public void deleteCartProduct(User user, Long productId) {
        user.getCartProducts().removeIf(cartProduct ->
                cartProduct.getProduct().getId().equals(productId));
        userRepository.save(user);
    }
}

