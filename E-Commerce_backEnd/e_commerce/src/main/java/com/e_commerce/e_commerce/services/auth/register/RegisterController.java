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

@RestController
@RequestMapping("market")
public class RegisterController {
    
    @Autowired
    RegisterServices registerServices;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody Map<String, String> credentials) {

        String userName = credentials.get("userName");
        String firstName = credentials.get("firstName");
        String lastName = credentials.get("lastName");
        String email = credentials.get("email");
        String password = credentials.get("password");

        String signUpResult = registerServices.signUp(firstName, lastName, userName, email, password);
        ResponseHelper responseHelper = new ResponseHelper();
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
}
