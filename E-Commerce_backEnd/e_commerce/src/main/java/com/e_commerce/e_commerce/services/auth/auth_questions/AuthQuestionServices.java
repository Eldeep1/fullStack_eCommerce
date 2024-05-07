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
    private final AuthQuestionModel authQuestionModel;
    private final ResponseHelper responseHelper;

    public ResponseEntity<Object> getAuthQuestionsServ() {
        try {
            if(authQuestionModel.getAuthQuestions() == null)
                throw new Exception("There is no questions ");
            return responseHelper.createSuccessResponse("returned sucessfully", authQuestionModel.getAuthQuestions());
        } catch (Exception e) {
            throw new RuntimeException("Error while getting questions ! "+ e.getMessage());
        }
    }

}
