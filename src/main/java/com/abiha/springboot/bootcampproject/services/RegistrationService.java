package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.dto.UserDto;
import com.abiha.springboot.bootcampproject.exception.BadRequestException;
import com.abiha.springboot.bootcampproject.entities.*;
import com.abiha.springboot.bootcampproject.repos.RoleRepo;
import com.abiha.springboot.bootcampproject.repos.TokenRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegistrationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<Object> registerUser(UserDto dto)
    {

        List<String> list = new ArrayList<>();
        User newUser =new User();


        newUser.setFirstName(dto.getFirstName());
        newUser.setMiddleName(dto.getMiddleName());
        newUser.setLastName(dto.getLastName());

        newUser.setEmail(dto.getEmail());

        if(! dto.getPassword().equals(dto.getConfirmPassword())){
            throw new BadRequestException("Passwords not matched");
        }

        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        if(dto.getContact()!=null)
        {
            Role role= roleRepo.findById(3L).get();

            HashSet<Role> roles = new HashSet<>();
            HashSet<User> users = new HashSet<>();

            users.add(newUser);
            role.setUser(users);

            roles.add(role);
            newUser.setRoles(roles);

            Customer newCustomer=new Customer();

            newCustomer.setContact(dto.getContact());
            newCustomer.setUser(newUser);
            newUser.setCustomer(newCustomer);

            newUser.setActive(Boolean.FALSE);
            newUser.setExpired(Boolean.FALSE);
            newUser.setLocked(Boolean.FALSE);
            newUser.setDeleted(Boolean.FALSE);
            newUser.setInvalidAttemptCount(0);
            newUser.setPasswordUpdateDate(new Date());

            User savedUser=userRepo.save(newUser);

            mailSend(newUser);

            return new ResponseEntity<>("Registration done Successfully! Activation Token sent to your email.", HttpStatus.OK);
        }

        if(dto.getCompanyName()!=null)
        {
            Role role= roleRepo.findById(2L).get();

            HashSet<Role>  roles = new HashSet<>();
            HashSet<User> users = new HashSet<>();

            users.add(newUser);
            role.setUser(users);

            roles.add(role);
            newUser.setRoles(roles);

            Seller newSeller= new Seller();

            newSeller.setGst(dto.getGst());
            newSeller.setCompanyName(dto.getCompanyName());
            newSeller.setCompanyContact(dto.getCompanyContact());

            newSeller.setUser(newUser);
            newUser.setSeller(newSeller);

            Address newAddress=new Address();
            Set<Address> addresses=new HashSet<>();

            newAddress.setCity(dto.getCompanyAddress().getCity());
            newAddress.setState(dto.getCompanyAddress().getState());
            newAddress.setCountry(dto.getCompanyAddress().getCountry());

            newAddress.setUser(newUser);
            addresses.add(newAddress);

            newUser.setAddresses(addresses);

            newUser.setActive(Boolean.FALSE);
            newUser.setExpired(Boolean.FALSE);
            newUser.setLocked(Boolean.FALSE);
            newUser.setDeleted(Boolean.FALSE);
            newUser.setInvalidAttemptCount(0);
            newUser.setPasswordUpdateDate(new Date());

            User savedUser=userRepo.save(newUser);

            emailService.sendSimpleMailMessage(newUser.getEmail(), "Account Created","Hello! your seller account has been created,waiting for approval.");

            return new ResponseEntity<>("Registration done Successfully!", HttpStatus.OK);
        }
        return null;
    }

    public void mailSend(User newUser){
        Token token = new Token(newUser);
        tokenRepo.save(token);
        String message = "To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+ token.getActivationToken();

        emailService.sendSimpleMailMessage(newUser.getEmail(), "Account Activation",message);
    }
}
