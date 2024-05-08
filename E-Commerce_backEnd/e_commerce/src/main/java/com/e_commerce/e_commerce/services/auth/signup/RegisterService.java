package com.e_commerce.e_commerce.services.auth.signup;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
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

    public ResponseEntity<Object> signupServ(@RequestBody Map<String, Object> credentials) {
        // The validation in AOP
        User user = hashedCredentials(credentials);
        return signupResult(user);
    }

    public ResponseEntity<Object> addAdminServ(@RequestBody Map<String, Object> credentials){

        try {

            String hashedPassword=(String)credentials.get("password");
            hashedPassword=securityHelper.hashString(hashedPassword);

            credentials.put("password", hashedPassword);
            credentials.put("role", "ADMIN");

            String hashedAnswer=(String)credentials.get("questinoAnswer");
            hashedAnswer=securityHelper.hashString(hashedAnswer);
            credentials.put("questinoAnswer", hashedAnswer);

            User user = new User(credentials);

            String signUpResult = registerModel.signUp(user);
            if (signUpResult.equals("created successfully")) {

                return responseHelper.createSuccessResponse("admin created successfully", null);
            } else if (signUpResult.equals("used Email")) {
                throw new Exception("The Email is Already Used");

            } else if (signUpResult.equals("database error")) {
                throw new Exception("Internal Database Error");
            }
            else {
                throw new Exception("Internal Error ");

            }

        } catch (Exception e){
            throw new RuntimeException("Error while adding admin "+e.getMessage());
        }

    }

    public ResponseEntity<Object> signupResult (User user){

        try {
            String signUpResult = registerModel.signUp(user);
            if (signUpResult.equals("created successfully"))
                return responseHelper.createSuccessResponse("account created successfully",null);

             else if (signUpResult.equals("used Email"))
                throw new Exception("The email is already exist");

            else
                throw new Exception("Internal Database Error");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public User hashedCredentials (Map<String,Object> credentials){

        String hashedPassword = (String) credentials.get("password");
        hashedPassword = securityHelper.hashString(hashedPassword);

        credentials.put("password", hashedPassword);
        credentials.put("role", "USER");

        String hashedAnswer = (String) credentials.get("questinoAnswer");
        hashedAnswer = securityHelper.hashString(hashedAnswer);
        credentials.put("questinoAnswer", hashedAnswer);
        User user = new User(credentials);
        return user;
    }



}
