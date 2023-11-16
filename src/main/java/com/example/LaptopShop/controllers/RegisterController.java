package com.example.LaptopShop.controllers;

import com.example.LaptopShop.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }
}
