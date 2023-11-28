package com.example.LaptopShop.config;

import com.example.LaptopShop.services.NeoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final NeoUserDetailsService neoUserDetailsService;


    public SecurityConfig(NeoUserDetailsService neoUserDetailsService) {
        this.neoUserDetailsService = neoUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize -> {
                    authorize
                            .anyRequest().permitAll();
                })
                .userDetailsService(neoUserDetailsService)
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/product")
                        .failureUrl("/login?error")
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                )
                .rememberMe(remember -> remember
                        .key("uniqueAndSecret")
                        .rememberMeParameter("remember-me"))
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/login?accessDenied"))
        ;

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() { // to let spring boot know bcrypt is the password encoder. password comes from insomnia basic auth.
        return new BCryptPasswordEncoder();
    }

}
