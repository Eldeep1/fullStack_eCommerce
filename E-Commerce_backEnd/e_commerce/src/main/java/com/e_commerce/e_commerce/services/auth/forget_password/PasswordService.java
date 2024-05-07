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
    private final ResponseHelper responseHelper;
    private final PasswordModel passwordModel;

    public ResponseEntity<Object> resettingPasswordServ(@RequestBody Map<String,String> data) {
        try {
            if (!passwordModel.verifyUser(data.get("question_id"), data.get("email"),data.get("answer"))) {
                throw new Exception("Credentials are wrong");
            }

            if(passwordModel.resetPassword(data.get("newPassword"), data.get("email"))){
                return responseHelper.createSuccessResponse("password is resetted successfully", null);
            }
            else {
                throw new Exception("Internal Exception : Can not reset the password");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Object> checkEmailServ(@RequestBody Map<String,String> data) {
        try {
            String email=data.get("email");
            if(passwordModel.checkForEmail(email)){
                return responseHelper.createSuccessResponse("email exists", null);
            }
            else
                throw  new Exception("can not find email");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    
}
