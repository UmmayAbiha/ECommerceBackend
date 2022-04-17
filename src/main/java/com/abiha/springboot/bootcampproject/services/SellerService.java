package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.dto.SellerDto;
import com.abiha.springboot.bootcampproject.dto.UserDto;
import com.abiha.springboot.bootcampproject.entities.Address;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.AddressRepo;
import com.abiha.springboot.bootcampproject.repos.SellerRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellerService {

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    public ResponseEntity<Object> updateSellerProfile(SellerDto sellerDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        } else {

            User user = (User) authentication.getPrincipal();

            if (sellerDto.getFirstName() != null)
                user.setFirstName(sellerDto.getFirstName());

            if (sellerDto.getLastName() != null) {
                user.setLastName(sellerDto.getLastName());
            }
            if (sellerDto.getMiddleName() != null) {
                user.setMiddleName(sellerDto.getMiddleName());
            }
            if (sellerDto.getGst() != null) {
                user.getSeller().setGst(sellerDto.getGst());
            }
            if (sellerDto.getCompanyName() != null) {

                if(!Objects.isNull(sellerRepo.findByCompanyName(sellerDto.getCompanyName()))){
                    return new ResponseEntity<>("This company name is already registered, enter unique company name!", HttpStatus.BAD_REQUEST);
                }
                user.getSeller().setCompanyName(sellerDto.getCompanyName());
            }
            if (sellerDto.getCompanyContact() != null) {
                user.getSeller().setCompanyContact(sellerDto.getCompanyContact());
            }

            userRepo.save(user);
            return new ResponseEntity<>("Profile Updated Successfully!",HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> updateSellerPassword(SellerDto sellerDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        } else {

            User user = (User) authentication.getPrincipal();

            if (sellerDto.getPassword().equals(sellerDto.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(sellerDto.getPassword()));
                userRepo.save(user);
                emailService.sendSimpleMailMessage(user.getEmail(),"Password Updated!",
                        "Hello User, Your password has been updated successfully. Warm regards");
                return new ResponseEntity<>("Password updated successfully!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Password and confirm password not matched!", HttpStatus.BAD_REQUEST);
        }
    }



    public ResponseEntity<Object> updateAddress(Long id, UserDto dto) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication==null || !authentication.isAuthenticated())
            return null;
        else {
            User user = (User) authentication.getPrincipal();

            Boolean aBoolean = addressRepo.findById(id).isPresent();

            if (aBoolean == true) {
                Address address = addressRepo.findById(id).get();

                System.out.println(user.getId());
                System.out.println(address.getUser().getId());

                    if (dto.getCompanyAddress().getAddressLine() != null)
                        address.setAddressLine(dto.getCompanyAddress().getAddressLine());

                    if (dto.getCompanyAddress().getCity() != null)
                        address.setCity(dto.getCompanyAddress().getCity());

                    if (dto.getCompanyAddress().getState() != null)
                        address.setState(dto.getCompanyAddress().getState());

                    if (dto.getCompanyAddress().getCountry() != null)
                        address.setCountry(dto.getCompanyAddress().getCountry());

                    if (dto.getCompanyAddress().getZipCode() != null)
                        address.setZipCode(dto.getCompanyAddress().getZipCode());

                    if (dto.getCompanyAddress().getLabel() != null)
                        address.setLabel(dto.getCompanyAddress().getLabel());

                Set<Address> addressSet = new HashSet<>();
                addressSet.add(address);
                user.setAddresses(addressSet);
                userRepo.save(user);

                return new ResponseEntity<>("Address updated Successfully!", HttpStatus.OK);
                }

                return new ResponseEntity<>("You entered incorrect Address ID", HttpStatus.NOT_FOUND);

            }

        }
    }


