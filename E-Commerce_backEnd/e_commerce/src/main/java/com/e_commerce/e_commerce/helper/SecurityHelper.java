package com.e_commerce.e_commerce.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.security_configurations.jwt.JwtService;

@Component
public class SecurityHelper {
    @Autowired
    private  PasswordEncoder passwordEncoder ;
    private JwtService jwtService = new JwtService();
    
    public String hashString(String plainText){

        return passwordEncoder.encode(plainText);
    }

    public String generateToken(String email){
        
        return jwtService.generateToken(email);
    }
}
