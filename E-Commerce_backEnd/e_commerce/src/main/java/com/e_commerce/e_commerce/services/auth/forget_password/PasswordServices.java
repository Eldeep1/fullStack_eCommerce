package com.e_commerce.e_commerce.services.auth.forget_password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.repositories.UserRepository;

@Service
public class PasswordServices {
    @Autowired
    private UserRepository userRepository ;

    public Boolean verifyUser(Integer questionId, String email, String answer){
        return userRepository.verifyUser(questionId, answer, email);
    }
    public Boolean resetPassword(String newPassword, String email){
        return userRepository.resetPassword(email, newPassword);
    }
    public Boolean checkForEmail(String email){
        try {
            userRepository.findByEmail(email);
            return true;   
        } catch (Exception e) {
            return false;
        }
    }

    
}
