package com.e_commerce.e_commerce.services.auth.multi_factor_auth.auth_questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("market")
public class AuthQuestionController {

    @Autowired
    private AuthQuestionsServices authQuestionsServices;
    private ResponseHelper responseHelper = new ResponseHelper();

    @GetMapping("auth/questions")
    public ResponseEntity<Object> getQuestions() {
        try {
            return responseHelper.createSuccessResponse("questions Loaded Successfully",authQuestionsServices.getQuestions());
            
        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error Loading questions ",null);
 
        }
    }
    
}
