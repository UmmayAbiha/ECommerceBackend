package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.entities.Token;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.TokenRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import com.abiha.springboot.bootcampproject.services.ForgotPasswordService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ForgotPasswordController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    TokenRepo tokenRepo;

    @Autowired
    ForgotPasswordService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = {"/forgot-password"})
    public ResponseEntity<Object> forgotPassword(@RequestBody ObjectNode objectNode) {
        String email = objectNode.get("email").asText();
        System.out.println(email);

        User user = service.forgotPassword(email);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else if (!user.isActive) {
            return new ResponseEntity<>("Inactive account!", HttpStatus.BAD_REQUEST);
        } else {
            System.out.println("User found");
            return new ResponseEntity<>("Reset Password link sent to your registered email!", HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping(value = {"/reset-password"})
    public ResponseEntity<Object> resetPassword(@RequestParam("token") String confirmationToken, @RequestBody ObjectNode objectNode) {
        Token token = tokenRepo.findByActivationToken(confirmationToken);

        System.out.println(objectNode.fieldNames());
        if (token != null) {
            User user = userRepo.findByEmail(token.getUserEntity().getEmail());

            if (token.getExpiryDate().before(new Date())) {
                tokenRepo.deleteById(token.getTokenid());
                return new ResponseEntity<>("Password not updated because token has expired.", HttpStatus.BAD_REQUEST);
            }
            String newPassword = objectNode.get("newPassword").asText();
            String confirmPassword = objectNode.get("confirmPassword").asText();
            //validation
            if (newPassword.equals(confirmPassword)) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepo.save(user);
                tokenRepo.deleteById(token.getTokenid());

                return new ResponseEntity<>("Password updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("New Password and Confirm Password do not match", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Token Invalid!", HttpStatus.NOT_ACCEPTABLE);

    }
}
