package com.e_commerce.e_commerce.services.auth.forget_password;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("market/auth")
public class PasswordController {
    @Autowired 
    private PasswordServices passwordServices;
    private ResponseHelper responseHelper = new ResponseHelper();


    @PutMapping("resetPassword")
    public ResponseEntity<Object>  resettingpassword(@RequestBody Map<String,String> data) {
        
        //1-check if the question and the answer exists
        //2- reset the password
        
        try {
            try {
                if (!passwordServices.verifyUser(Integer.parseInt(data.get("question_id")), data.get("email"),data.get("answer"))) {
                     return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "credantials are wrong",null);

                }
            } catch (Exception e) {
                return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "an error happened",null);
            }
           return passwordServices.resetPassword(data.get("newPassword"), data.get("newEmail"))?
           responseHelper.createSuccessResponse("password resetted successfully", null):
           responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "can not reset the password",null);
        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "can not reset the password",null);
        }
    }

    @PostMapping("checkMail")
    public ResponseEntity<Object>  checkEmail(@RequestBody Map<String,String> data) {
        return passwordServices.checkForEmail(data.get("email"))?
        responseHelper.createSuccessResponse("email exists", null):
        responseHelper.createErrorResponse(HttpStatus.NOT_FOUND, "can not find email",null);
        
    }
    
}
