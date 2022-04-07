package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token,Long> {

    Token findByUserEmail(String userEmail);
    Token findByActivationToken(String activationToken);

    Token findByUserEmailAndActivationToken(String userEmail, String activationToken);

}