package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token,Long> {
    Token findByActivationToken(String activationToken);

}