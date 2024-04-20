package com.e_commerce.e_commerce.services.auth.reset_password;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("market")
public class RessetingController {
    @Autowired 
    private RessetingServices ressetingServices;
    private ResponseHelper responseHelper = new ResponseHelper();


    @PutMapping("auth/resetPassword")
    public ResponseEntity<Object>  resettingpassword(@RequestBody Map<String,String> data) {
        
        try {
           return ressetingServices.resetPassword(data.get("newPassword"), data.get("newEmail"))?
           responseHelper.createSuccessResponse("password resetted successfully", null):
           responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "can not reset the password",null);
        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "can not reset the password",null);
        }
    }
}
