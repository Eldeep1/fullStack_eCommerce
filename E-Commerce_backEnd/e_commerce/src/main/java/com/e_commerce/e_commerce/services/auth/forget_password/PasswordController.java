package com.e_commerce.e_commerce.services.auth.forget_password;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.Validation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("market/auth")
@AllArgsConstructor
public class PasswordController {
    @Autowired 
    private final PasswordService passwordServices;
    private ResponseHelper responseHelper = new ResponseHelper();


    @PutMapping("resetPassword")
    public ResponseEntity<Object>  resettingpassword(@RequestBody Map<String,String> data) {
        return passwordServices.resettingpasswordServ(data);
    }

    @PostMapping("checkMail")
    public ResponseEntity<Object>  checkEmail(@RequestBody Map<String,String> data) {
       return passwordServices.checkEmailServ(data);
    }
    
}
