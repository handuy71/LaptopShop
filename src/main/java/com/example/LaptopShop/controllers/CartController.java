package com.example.LaptopShop.controllers;

import com.example.LaptopShop.DTO.CartProductDTO;
import com.example.LaptopShop.models.User;
import com.example.LaptopShop.services.CartService;
import com.example.LaptopShop.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class CartController {
    private final UserService userService;
    private final CartService cartService;

    public CartController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @ModelAttribute("loggedInUser")
    public User loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(auth.getName());
    }
    public User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/cart")
    public String getCartProductsByUser(Model model, HttpServletRequest request) {
        User user = getSessionUser(request);
        List<CartProductDTO> cartProducts = cartService.getCartProductsByUser(user);

        model.addAttribute("cartProducts", cartProducts);

        return "client/cart";
    }


}
