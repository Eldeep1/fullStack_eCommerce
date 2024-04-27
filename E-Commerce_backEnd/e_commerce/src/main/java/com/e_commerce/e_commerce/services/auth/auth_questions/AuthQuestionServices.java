package com.e_commerce.e_commerce.services.auth.auth_questions;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.repositories.AuthQuestionsRepo;

@Service
@AllArgsConstructor
public class AuthQuestionServices {

    @Autowired
    private final AuthQuestionsRepo authQuestionsRepo;
    private final ResponseHelper responseHelper;

    public Object getAuthQuestions(){
        return authQuestionsRepo.findAll();
    }

    public ResponseEntity<Object> getAuthQuestionsServ() {

        try {
            return responseHelper.createSuccessResponse("returned sucessfully", getAuthQuestions());
        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.BAD_REQUEST, "can't load auth questions", null);
        }
    }

}
