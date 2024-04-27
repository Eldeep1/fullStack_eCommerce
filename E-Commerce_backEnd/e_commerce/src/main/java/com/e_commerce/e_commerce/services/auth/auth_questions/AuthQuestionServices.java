package com.e_commerce.e_commerce.services.auth.auth_questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.repositories.AuthQuestionsRepo;

@Service
public class AuthQuestionServices {
    @Autowired
    AuthQuestionsRepo authQuestionsRepo;
    public Object getAuthQuestions(){
        return authQuestionsRepo.findAll();
    }
}
