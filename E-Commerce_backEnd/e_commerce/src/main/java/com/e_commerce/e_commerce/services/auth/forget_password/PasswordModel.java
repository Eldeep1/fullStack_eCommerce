package com.e_commerce.e_commerce.services.auth.forget_password;

import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class PasswordModel {
    private final UserRepository userRepository ;
    private final SecurityHelper securityHelper;

    public Boolean checkForEmail(String email){
        try {
            if (userRepository.findByEmail(email) == null) {
                return false;
            };
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean verifyUser(String questionId, String email, String answer){
        try{
            User user=userRepository.findByEmail(email);
            if ((user !=null) && (Boolean.TRUE.equals(securityHelper.checkHashEquality(answer, user.getAuthQuestionAnswer()))&& Objects.equals(user.getQuestionId(), questionId))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean resetPassword(String newPassword, String email){

        String hashedNewPassword=securityHelper.hashString(newPassword);
        return userRepository.resetPassword(hashedNewPassword, email)>0;
    }
}
