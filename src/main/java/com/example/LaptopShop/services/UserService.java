package com.example.LaptopShop.services;

import com.example.LaptopShop.models.User;
import com.example.LaptopShop.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    ;

    public User registerUser(User user) {
        // Check if username is already taken
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }

        // Check if email is already taken
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // Check if passwords match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Mật khẩu không khớp");
        }

        // Set default role "ROLE_CUSTOMER"
        user.setRoles("ROLE_CUSTOMER");

        // Encode password before saving to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user
        return userRepository.save(user);
    }

    public User updateInformation(User user, String newName,String newEmail, String newPhone, String newAddress) {
        if (newName != "") {
            user.setName(newName);
        }
        if (newEmail != "") {
            user.setEmail(newEmail);
        }
        if (newPhone != "") {
            user.setPhone(newPhone);
        }
        if (newAddress != "") {
            user.setAddress(newAddress);
        }
        return userRepository.save(user);
    }
    public boolean updatePassword(User user, String oldPassword, String newPassword, String confirmPassword) {
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword()) && newPassword.equals(confirmPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false; // or throw an exception indicating failure
    }
}