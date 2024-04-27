package com.e_commerce.e_commerce.models;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="auth_questions")
public class AuthQuestions {

    @Id
    private int id;
    private String question;

    public Map<Object, Object> authQuestionsToMap(){
        Map<Object, Object> data = new LinkedHashMap<>();

        // Add user properties to the map
        data.put("id", id);
        data.put("question",question);

        return data;
    }

}
