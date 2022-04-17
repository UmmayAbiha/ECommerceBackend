package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.dto.UserDto;
import com.abiha.springboot.bootcampproject.entities.Token;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.TokenRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import com.abiha.springboot.bootcampproject.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;


    @PostMapping("/userRegister")
    public ResponseEntity<Object> userRegistration(@Valid @RequestBody UserDto userDto) {

        if(userRepo.findByEmail(userDto.getEmail())!=null)
            return new ResponseEntity<>("Duplicate email entered!", HttpStatus.OK);

        return registrationService.registerUser(userDto);

    }

    @PostMapping("/confirm-account")
    public ResponseEntity<Object> confirmAccount(@RequestParam("token") String confirmationToken) {
        Token token = tokenRepo.findByActivationToken(confirmationToken);

        if (token != null) {

            User user = userRepo.findByEmail(token.getUserEntity().getEmail());

            if(token.getExpiryDate().before(new Date())){
                registrationService.mailSend(user);
                tokenRepo.deleteById(token.getTokenid());
                return new ResponseEntity<>("Account inactive, token has expired.New link sent to your account",HttpStatus.BAD_REQUEST);
            }

            user.setActive(true);
            user.setLocked(false);
            userRepo.save(user);

            tokenRepo.deleteById(token.getTokenid());

            return new ResponseEntity<>("Account Activated Successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid token!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/resent-activation-link")
    public ResponseEntity<Object> resendLink(@RequestParam("email") String email) {

        User user=userRepo.findByEmail(email);

            if(user!=null && !user.isActive){
                registrationService.mailSend(user);
                return new ResponseEntity<>("Activation link has been sent again",HttpStatus.OK);
            } else if (user!=null && user.isActive) {
                return new ResponseEntity<>("Already an active user", HttpStatus.BAD_REQUEST);

            }
        return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
    }

}

