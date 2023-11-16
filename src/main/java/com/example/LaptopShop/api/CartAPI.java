package com.example.LaptopShop.api;

import com.example.LaptopShop.DTO.CartProductDTO;
import com.example.LaptopShop.models.CartProduct;
import com.example.LaptopShop.models.User;
import com.example.LaptopShop.services.CartService;
import com.example.LaptopShop.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartAPI {
    private final UserService userService;
    private final CartService cartService;

    public CartAPI(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/add-to-cart/{productId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> addToCart(@PathVariable Long productId, HttpServletRequest request) {
        User user = getSessionUser(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in.");
        }

        cartService.addProductToCart(user, productId);
        return ResponseEntity.ok("Product added to cart");
    }
    @GetMapping("/changeQuantity")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> changeQuantity(@RequestParam Long productId,@RequestParam int quantity, HttpServletRequest request) {
        User user = getSessionUser(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in.");
        }

        cartService.updateCartProductQuantity(user, productId, quantity);
        return ResponseEntity.ok("Quantity changed");
    }
    @GetMapping("/remove-from-cart/{productId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> removeFromCart(@PathVariable Long productId, HttpServletRequest request) {
        User user = getSessionUser(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in.");
        }

        cartService.deleteCartProduct(user, productId);
        return ResponseEntity.ok("Product removed from cart");
    }

    private User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }
}

