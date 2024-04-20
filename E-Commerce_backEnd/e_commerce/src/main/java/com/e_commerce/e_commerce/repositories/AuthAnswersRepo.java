package com.e_commerce.e_commerce.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.e_commerce.models.AuthAnswers;

@Repository
public interface AuthAnswersRepo extends CrudRepository<AuthAnswers,Integer> {
    
}
