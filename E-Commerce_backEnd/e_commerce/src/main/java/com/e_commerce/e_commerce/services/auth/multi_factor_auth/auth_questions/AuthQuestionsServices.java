package com.e_commerce.e_commerce.services.auth.multi_factor_auth.auth_questions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.models.AuthQuestions;
import com.e_commerce.e_commerce.repositories.AuthQuestionsRepo;

@Service
public class AuthQuestionsServices {
    
    @Autowired
    private AuthQuestionsRepo authQuestionsRepo;
    public List<AuthQuestions> getQuestions(){
        return (List<AuthQuestions>) authQuestionsRepo.findAll();
    }
}
