package com.e_commerce.e_commerce.services.auth.login;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.helper.Validation;
import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@AllArgsConstructor
public class LoginService {
    @Autowired
    private final UserRepository userRepository;
    private final SecurityHelper securityHelper;
    private final ResponseHelper responseHelper;
    private final Validation validation;

    public User login(String email, String password){
        User user =userRepository.findByEmail(email);
        if (securityHelper.checkHashEquality(password, user.getPassword())) {
            return user;
        }
        else{
            return null;
        }
    }

    public ResponseEntity<Object> loginUserServ(@RequestBody Map<String, String> credentials) {

        String email = credentials.get("email");
        String password = credentials.get("password");
        User authenticatedUser = login(email, password);
        // The validation in the loginAop
        String token = securityHelper.generateToken(email);
        Map<Object, Object> userData = authenticatedUser.userToMap();
        userData.put("token", token);
        return responseHelper.createSuccessResponse("login Successfully", userData);
    }

}
