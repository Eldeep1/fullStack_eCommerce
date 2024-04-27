package com.e_commerce.e_commerce.services.auth.signup;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.helper.Validation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.models.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@AllArgsConstructor
public class RegisterService {

    @Autowired
    private final RegisterModel registerModel;
    private final ResponseHelper responseHelper;
    private final SecurityHelper securityHelper;

    public  String signUp(User user){
        return registerModel.createUser(user);
    }

    public ResponseEntity<Object> signupServ(@RequestBody Map<String, Object> credentials) {
        
        String hashedPassword = (String) credentials.get("password");
        hashedPassword = securityHelper.hashString(hashedPassword);

        credentials.put("password", hashedPassword);
        credentials.put("role", "USER");

        String hashedAnswer = (String) credentials.get("questinoAnswer");
        hashedAnswer = securityHelper.hashString(hashedAnswer);
        credentials.put("questinoAnswer", hashedAnswer);


        User user = new User(credentials);

        String signUpResult = signUp(user);
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
