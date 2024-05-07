package com.e_commerce.e_commerce.services.auth.auth_questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;

@RestController
@RequestMapping("market/auth")
public class AuthQuestionController {
    @Autowired 
    private AuthQuestionServices authQuestionServices;

    @GetMapping("questinos")
    public ResponseEntity<Object>  getAuthQuestions() {
        return authQuestionServices.getAuthQuestionsServ();
    }
}
