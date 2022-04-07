package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.model.Token;
import com.abiha.springboot.bootcampproject.model.User;
import com.abiha.springboot.bootcampproject.repos.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private TokenRepo tokenRepo;

    public Token generateActivationToken(User user){
        Token token = new Token();
        token.setUserEmail(user.getEmail());
        token.setActivationToken(UUID.randomUUID().toString());
       // token.setCreatedAt(LocalDateTime.now());
        return tokenRepo.save(token);
    }

    public Token validateToken(Token token){
        return tokenRepo.findByUserEmailAndActivationToken(token.getUserEmail(),token.getActivationToken());
    }

}
