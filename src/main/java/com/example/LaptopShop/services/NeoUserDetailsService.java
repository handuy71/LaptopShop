package com.example.LaptopShop.services;
import com.example.LaptopShop.models.User;
import com.example.LaptopShop.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NeoUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public NeoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // username comes from insomnia basic auth
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

//        Set<GrantedAuthority> grantedAuthorities = (Set<GrantedAuthority>) user.getAuthorities();
//        Set<VaiTro> roles = user.getAuthorities();
//        for (VaiTro role : roles) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getTenVaiTro()));
//        }
        return user;
    }
}