package com.e_commerce.e_commerce.services.auth.reset_password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.repositories.UserRepository;

@Service
public class RessetingServices {
    @Autowired
    private UserRepository userRepository ;
    public Boolean resetPassword(String newPassword, String email){
        return userRepository.resetPassword(email, newPassword);
    }
}
