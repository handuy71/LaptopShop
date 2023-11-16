package com.example.LaptopShop.api;

import com.example.LaptopShop.models.User;
import com.example.LaptopShop.services.CartService;
import com.example.LaptopShop.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class UserAPI {
    private final UserService userService;
    private final CartService cartService;

    public UserAPI(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping("/updateInfo")
    @PostAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> updateInfo(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) String phone,
                                           @RequestParam(required = false) String address,
                                           HttpServletRequest request) {
        User user = getSessionUser(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in.");
        }

        userService.updateInformation(user,name,email,phone,address);
        return ResponseEntity.ok("User information updated");
    }
    @PostAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam String confirmPassword,HttpServletRequest request) {
        User user = getSessionUser(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in.");
        }
        boolean success = userService.updatePassword(user, oldPassword, newPassword, confirmPassword);
        if (success) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update password. Please check your old password and ensure the new passwords match.");
        }
    }

    private User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }
}
