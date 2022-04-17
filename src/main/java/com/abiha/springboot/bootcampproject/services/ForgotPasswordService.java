package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.entities.Token;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.TokenRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private EmailService emailService;

    Boolean userExist(String email) {
        return userRepo.findByEmail(email) != null ? true : false;
    }


    public User forgotPassword(String email) {
        System.out.println(email);
        boolean ifExist = userExist(email);
        if (ifExist) {
            User user = userRepo.findByEmail(email);
            System.out.println(user.getFirstName());

            Token token = new Token(user);
            tokenRepo.save(token);
            user.setResetPasswordToken(token.getActivationToken());

            String message = "To reset your password, please click here : "
                    + "http://localhost:8080/reset-password?token=" + token.getActivationToken();

            emailService.sendSimpleMailMessage(user.getEmail(), "Forgot Password!", message);
            return user;
        } else {
            System.out.println("User is null");
            return null;

        }
    }
}

