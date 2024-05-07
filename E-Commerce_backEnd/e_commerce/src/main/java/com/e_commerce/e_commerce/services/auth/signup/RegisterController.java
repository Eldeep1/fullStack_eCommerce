package com.e_commerce.e_commerce.services.auth.signup;

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
public class RegisterController {

    @Autowired
    private RegisterService registerServices;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody Map<String, Object> credentials) {
        return registerServices.signupServ(credentials);
    }

    @PostMapping("addAdmin")
    public ResponseEntity<Object> addAdmin(@RequestBody Map<String, Object> credentials) {
        return registerServices.addAdminServ(credentials);
    }


}
