package com.e_commerce.e_commerce.services.auth.forget_password;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.Validation;
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

    public ResponseEntity<Object> resettingpasswordServ(@RequestBody Map<String,String> data) {

        //1-check if the question and the answer exists
        //2- reset the password
        //here, we should get email , questino id, questino answer, and new password!
        try {

            return resetPassword(data.get("newPassword"), data.get("email"))?
                    responseHelper.createSuccessResponse("password resetted successfully", null):
                    responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "can not reset the password",null);
        } catch (Exception e) {
            System.out.println(e);
            return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "can not reset the password",e);
        }
    }

    public ResponseEntity<Object>  checkEmailServ(@RequestBody Map<String,String> data) {
        String email=data.get("email");
            return checkForEmail(email)?
                    responseHelper.createSuccessResponse("email exists", null):
                    responseHelper.createErrorResponse(HttpStatus.NOT_FOUND, "can not find email",null);
    }

    
}
