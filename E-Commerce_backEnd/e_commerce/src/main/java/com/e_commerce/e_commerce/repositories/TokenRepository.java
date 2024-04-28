package com.e_commerce.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.e_commerce.e_commerce.models.Token;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer>{

    @Query("""
            Select t from Token t inner join User u
            on t.user.id = u.id
            where t.user.id = :userId and t.loggedOut = false
            """)
    List<Token> findAllTokenByUser(Integer userId);

    Optional<Token> findByToken(String token);
}
