package com.e_commerce.e_commerce.services.auth.register;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.Validation;
import com.e_commerce.e_commerce.models.User;

@RestController
@RequestMapping("market")
public class RegisterController {
    
    @Autowired
    RegisterServices registerServices;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody User user) {

        ResponseHelper responseHelper = new ResponseHelper();
        Validation validation= new Validation();
        Map<String, String> errors = validation.Validate(user);
        
        if(errors.isEmpty()) { 
            String signUpResult = registerServices.signUp(user.getFirstName(), user.getLastName(), user.getUserName(),user.getEmail(), user.getPassword());
            
            if (signUpResult.equals("created successfully")) {

                return responseHelper.createSuccessResponse("account created successfully", null);
            } else if (signUpResult.equals("used Email")) {
                return responseHelper.createErrorResponse(HttpStatus.BAD_REQUEST, "The Email is Already Used", null);
    
            } else if (signUpResult.equals("database error")) {
                return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Database Error",
                        null);
            }
            else {
                return responseHelper.createErrorResponse(HttpStatus.BAD_REQUEST, "Password is not valid", null);
    
            }    
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
    }
}
