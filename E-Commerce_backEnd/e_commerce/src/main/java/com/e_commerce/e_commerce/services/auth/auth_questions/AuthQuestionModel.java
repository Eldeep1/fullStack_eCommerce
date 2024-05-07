package com.e_commerce.e_commerce.services.auth.auth_questions;

import com.e_commerce.e_commerce.repositories.AuthQuestionsRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthQuestionModel {
    @Autowired
    private final AuthQuestionsRepo authQuestionsRepo;

    public Object getAuthQuestions(){
        return authQuestionsRepo.findAll();
    }
}
