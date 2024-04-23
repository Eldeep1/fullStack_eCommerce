package com.e_commerce.e_commerce.services.auth.forget_password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.repositories.UserRepository;

@Service
public class PasswordServices {
    @Autowired
    private UserRepository userRepository ;

    public Boolean verifyUser(String questionId, String email, String answer){
        if(userRepository.verifyUser(questionId, answer, email)==null){
            return false;
        }
        return true ;
    }
    public Boolean resetPassword(String newPassword, String email){
        
        return userRepository.resetPassword(newPassword, email)>0;
    }
    public Boolean checkForEmail(String email){
        try {
            if (userRepository.findByEmail(email) == null) {
                return false;
            };
            return true;   
        } catch (Exception e) {
            return false;
        }
    }

    
}
