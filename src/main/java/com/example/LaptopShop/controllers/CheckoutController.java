package com.example.LaptopShop.controllers;

import com.example.LaptopShop.DTO.CartProductDTO;
import com.example.LaptopShop.models.User;
import com.example.LaptopShop.services.CartService;
import com.example.LaptopShop.services.OrderService;
import com.example.LaptopShop.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PostAuthorize;
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
public class CheckoutController {
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    public CheckoutController(UserService userService, CartService cartService, OrderService orderService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
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
    @GetMapping("/checkout")
    public String orderPage(HttpServletRequest request, Model model){
        User user = getSessionUser(request);
        List<CartProductDTO> cartProducts = cartService.getCartProductsByUser(user);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("name", user.getName());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("email", user.getEmail());
        return "client/checkout";
    }

    @PostAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/thankyou")
    public String createOrder(HttpServletRequest request,@RequestParam String name,@RequestParam String phone,@RequestParam String address){
        User user = getSessionUser(request);
        orderService.createOrder(user,name,phone,address);
        return "client/thankYou";
    }
}
