package com.e_commerce.e_commerce.helper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.models.Cart;
import com.e_commerce.e_commerce.repositories.CartRepository;
import com.e_commerce.e_commerce.repositories.UserRepository;
import com.e_commerce.e_commerce.security_configurations.jwt.JwtService;

@Configuration
public class SecurityHelper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService = new JwtService();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    public String hashString(String plainText) {

        return passwordEncoder.encode(plainText);
    }

    public Boolean checkHashEquality(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Boolean checkUserCredantials(Map<String, Object> data) {

        String token = (String) data.get("token");
        String email = (String) data.get("email");
        Integer id = (Integer) data.get("userId");
        System.out.println("in checking for token");
        if (jwtService.extractEmail(token).equals(email)) {
            System.out.println("we checked the token already...");
            Integer userId = Integer.parseInt(userRepository.findByEmail(email).getId().toString());
            System.out.println(userId);
            System.out.println(id);
            if (userId.equals(id)) {
                System.out.println("we have reached so far already");
                return true;
            }
        }

        return false;
    }

    public Boolean checkCartOwner(Map<String, Object> data) {
        Integer userId = (Integer) data.get("userId");
        Integer cartId = (Integer) data.get("cartId");

        Cart cart = cartRepository.findById(cartId).orElse(null);

        return cart.getUserId() == userId;

    }

    public String generateToken(String email) {

        return jwtService.generateToken(email);
    }
}
