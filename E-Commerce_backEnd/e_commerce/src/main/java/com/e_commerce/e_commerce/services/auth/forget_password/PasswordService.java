package com.e_commerce.e_commerce.services.auth.forget_password;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PasswordService {
    @Autowired
    private final UserRepository userRepository ;
    private final  SecurityHelper securityHelper;
    private final ResponseHelper responseHelper;


    public ResponseEntity<Object> resettingPasswordServ(@RequestBody Map<String,String> data) {
        try {
            if (!verifyUser(data.get("question_id"), data.get("email"),data.get("answer"))) {
                throw new Exception("Credentials are wrong");
            }

            if(resetPassword(data.get("newPassword"), data.get("email"))){
                return responseHelper.createSuccessResponse("password is resetted successfully", null);
            }
            else {
                throw new Exception("can not reset the password");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Object>  checkEmailServ(@RequestBody Map<String,String> data) {
        try {
            String email=data.get("email");
            if(checkForEmail(email)){
                return responseHelper.createSuccessResponse("email exists", null);
            }
            else
                throw  new Exception("can not find email");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
            if ((user !=null) && (Boolean.TRUE.equals(securityHelper.checkHashEquality(answer, user.getAuthQuestionAnswer()))&&Objects.equals(user.getQuestionId(), questionId))) {
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
