package com.e_commerce.e_commerce.services.auth.multi_factor_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.models.AuthAnswers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("market/auth")
public class AuthQuestionController {

    @Autowired
    private AuthQuestionsServices authQuestionsServices;
    private ResponseHelper responseHelper = new ResponseHelper();

    @GetMapping("questions")
    public ResponseEntity<Object> getQuestions() {
        try {
            return responseHelper.createSuccessResponse("questions Loaded Successfully",authQuestionsServices.getQuestions());
            
        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error Loading questions ",null);
 
        }
    }
    @PostMapping("submitAnswer")
    public ResponseEntity<Object> submitAnswer(@RequestBody Map<String,Object> data) {
        
        AuthAnswers authAnswers=new AuthAnswers(data);
        return authQuestionsServices.sumbitAnswer(authAnswers)?
        responseHelper.createSuccessResponse("answer added Successfully",null)
        :responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "a problem happened",null)
        ;
    }
    
    @PostMapping("verifyAnswer")
    public ResponseEntity<Object> verifyAnswer(@RequestBody Map<String,Object> data) {
        AuthAnswers authAnswers=new AuthAnswers(data);

        return authQuestionsServices.verifyAnswer(authAnswers)?
        responseHelper.createSuccessResponse("answer is right",null)
        :responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "answer is wrong",null)
        ;
        
    }
    
    
}
