package com.e_commerce.e_commerce.services.auth.login;

import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;
import com.e_commerce.e_commerce.security_configurations.jwt.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {
    @Autowired
    private  UserRepository userRepository;

    public User login(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }
}
