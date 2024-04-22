package com.e_commerce.e_commerce.services.auth.login;

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
public class LoginController {
    @Autowired
    private LoginServices loginServices;
    private ResponseHelper responseHelper = new ResponseHelper();

    @PostMapping("login")
    public ResponseEntity<Object> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Validation validation= new Validation();
        Map<String, String> errors = validation.validateLogin(email);

        if(errors.isEmpty()){
        // Call the service layer to authenticate the user
        User authenticatedUser = loginServices.login(email, password);
        // If authentication is successful, return the user object
        if (authenticatedUser != null) {
            return responseHelper.createSuccessResponse("login Successfully",authenticatedUser.userToMap());
        } else {
            // If authentication fails, return an error response
            return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED,"Invalid Credentials",null);
        }
     }
     else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
     }
    }

}
