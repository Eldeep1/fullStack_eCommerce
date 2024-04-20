package com.e_commerce.e_commerce.services.auth.multi_factor_auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.models.AuthAnswers;
import com.e_commerce.e_commerce.models.AuthQuestions;
import com.e_commerce.e_commerce.repositories.AuthAnswersRepo;
import com.e_commerce.e_commerce.repositories.AuthQuestionsRepo;

@Service
public class AuthQuestionsServices {
    
    @Autowired
    private AuthQuestionsRepo authQuestionsRepo;
    @Autowired
    private AuthAnswersRepo authAnswersRepo;
    public List<AuthQuestions> getQuestions(){
        return (List<AuthQuestions>) authQuestionsRepo.findAll();
    }

    public Boolean sumbitAnswer(AuthAnswers authAnswers){

        try {
            authAnswersRepo.save(authAnswers);
            return true;
        } catch (Exception e) {

            return false;        
        }
    }
    
    public Boolean verifyAnswer(AuthAnswers authAnswers){
        AuthAnswers verifying = authAnswersRepo.findById(authAnswers.getId()).orElse(null);

        try {
           if (verifying==null) {
            return false;
           }
           if (verifying.equals(authAnswers)) {
            return true;
           }
           return false;
        } catch (Exception e) {

            return false;        
        }
    }
}
