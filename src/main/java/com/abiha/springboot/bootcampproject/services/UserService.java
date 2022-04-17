package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.dto.*;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.SellerRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    // seller customer api
    public ResponseEntity<Object> viewUserDetails(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        else {
            User user = (User)authentication.getPrincipal();

            CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
            SellerDetailsDto sellerDetailsDto = new SellerDetailsDto();

            if(user.getSeller() == null){
                customerDetailsDto.setId(user.getId());
                customerDetailsDto.setFirstName(user.getFirstName());
                customerDetailsDto.setLastName(user.getLastName());
                customerDetailsDto.setIsActive(user.isActive);
                customerDetailsDto.setContact(user.getCustomer().getContact());

                return new ResponseEntity(customerDetailsDto, HttpStatus.OK);
            }
            sellerDetailsDto.setId(user.getId());
            sellerDetailsDto.setFirstName(user.getFirstName());
            sellerDetailsDto.setLastName(user.getLastName());
            sellerDetailsDto.setIsActive(user.isActive);
            sellerDetailsDto.setGst(user.getSeller().getGst());
            sellerDetailsDto.setCompanyName(user.getSeller().getCompanyName());
            sellerDetailsDto.setCompanyContact(user.getSeller().getCompanyContact());
            sellerDetailsDto.setCompanyAddress(user.getAddresses());

            return new ResponseEntity<>(sellerDetailsDto,HttpStatus.OK);
        }
    }

}
