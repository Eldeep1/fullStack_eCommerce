package com.e_commerce.e_commerce.models;

import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="auth_answers")
public class AuthAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String userEmail;

    private int questionId;

    private String answer;

    public AuthAnswers(){
    }
    public AuthAnswers(Map<String,Object> data){
        this.answer=(String) data.get("answer");
        this.userEmail=(String) data.get("userEmail");
        this.questionId=(int) data.get("questionId");
    }

    public int getId(){
        return this.id;
    }
    public String getUserEmail(){
        return this.userEmail;
    }

    public int getQuestionId(){
        return this.questionId;
    }
    public String getAnswer(){
        return this.answer;
    }
}
