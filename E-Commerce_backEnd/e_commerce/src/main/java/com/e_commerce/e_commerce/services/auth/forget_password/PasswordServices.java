package com.e_commerce.e_commerce.services.auth.forget_password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;
import java.util.Objects;

@Service
public class PasswordServices {
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private SecurityHelper securityHelper;
    public Boolean verifyUser(String questionId, String email, String answer){
        User user=userRepository.findByEmail(email);

        if (Boolean.TRUE.equals(securityHelper.checkHashEquality(answer, user.getAuthQuestionAnswer()))&&Objects.equals(user.getQuestionId(), questionId)) {
            return true;
        }
        return false;

    }
    public Boolean resetPassword(String newPassword, String email){
        
        String hashedNewPassword=securityHelper.hashString(newPassword);
        return userRepository.resetPassword(hashedNewPassword, email)>0;
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
