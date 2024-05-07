package com.e_commerce.e_commerce.services.auth.login;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.helper.Validation;
import com.e_commerce.e_commerce.models.User;

@RestController
@RequestMapping("market/auth")
@AllArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService loginServices;

    @PostMapping("login")
    public ResponseEntity<Object> loginUser(@RequestBody Map<String, String> credentials) {
        return loginServices.loginUserServ(credentials);
    }

}
