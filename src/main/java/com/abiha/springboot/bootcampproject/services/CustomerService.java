package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.dto.AddressDto;
import com.abiha.springboot.bootcampproject.dto.CustomerDto;
import com.abiha.springboot.bootcampproject.entities.Address;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public ResponseEntity<Object> updateCustomerProfile(CustomerDto customerDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        } else {

            User user = (User) authentication.getPrincipal();

            if (customerDto.getFirstName() != null)
                user.setFirstName(customerDto.getFirstName());

            if (customerDto.getLastName() != null) {
                user.setLastName(customerDto.getLastName());
            }
            if (customerDto.getMiddleName() != null) {
                user.setMiddleName(customerDto.getMiddleName());
            }
            if (customerDto.getContact() != null) {
                user.getCustomer().setContact(customerDto.getContact());
            }

            userRepo.save(user);
            return new ResponseEntity<>("Profile Updated Successfully!",HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> updateCustomerPassword(CustomerDto customerDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        } else {
            User user = (User) authentication.getPrincipal();

            if (customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                userRepo.save(user);
                emailService.sendSimpleMailMessage(user.getEmail(),"Password Updated!",
                        "Hello User, Your password has been updated successfully. Warm regards");
                return new ResponseEntity<>("Password updated successfully!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Password and confirm password not matched!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> viewCustomerAddresses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            return null;
        else {
            User user = (User) authentication.getPrincipal();
            Set<Address> addresses = addressRepo.findByUserId(user.getId());

            if (addresses.isEmpty()) {
                return new ResponseEntity<>("No Address to show!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        }
    }


    public ResponseEntity<Object> addingCustomerAddress(AddressDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            return null;
        else {
            User user = (User) authentication.getPrincipal();

            Address address = new Address();

            address.setAddressLine(dto.getAddressLine());
            address.setCity(dto.getCity());
            address.setState(dto.getState());
            address.setCountry(dto.getCountry());
            address.setZipCode(dto.getZipCode());
            address.setLabel(dto.getLabel());

            address.setUser(user);

            user.addAddress(address);

            userRepo.save(user);
            return new ResponseEntity<>("Address Added Successfully!", HttpStatus.OK);
        }

    }

    public ResponseEntity<Object> updateCustomerAddress(Long id, AddressDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            return null;
        else {
            User user = (User) authentication.getPrincipal();

            Boolean aBoolean = addressRepo.findById(id).isPresent();

            if (aBoolean == true) {
                Address address = addressRepo.findById(id).get();
                System.out.println(user.getId());
                System.out.println(address.getUser().getId());

                if (address.getUser().getId() == user.getId()) {

                    if (dto.getAddressLine() != null)
                        address.setAddressLine(dto.getAddressLine());
                    if (dto.getCity() != null)
                        address.setCity(dto.getCity());
                    if (dto.getState() != null)
                        address.setState(dto.getState());
                    if (dto.getCountry() != null)
                        address.setCountry(dto.getCountry());
                    if (dto.getZipCode() != null)
                        address.setZipCode(dto.getZipCode());
                    if (dto.getLabel() != null)
                        address.setLabel(dto.getLabel());

                    Set<Address> addressSet = new HashSet<>();
                    addressSet.add(address);
                    user.setAddresses(addressSet);
                    userRepo.save(user);

                    return new ResponseEntity<>("Address updated Successfully!", HttpStatus.OK);
                }
                return new ResponseEntity<>("You entered incorrect Address ID", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Address doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> deleteCustomerAddress(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            return null;
        else {
            User user = (User) authentication.getPrincipal();

            Boolean aBoolean = addressRepo.findById(id).isPresent();

            if (aBoolean == true) {
                Address address = addressRepo.findById(id).get();
                addressRepo.deleteByAddressId(id);
                return new ResponseEntity<>("Address deleted successfully!", HttpStatus.OK);
            }
            return new ResponseEntity<>("No address is found for the address id!", HttpStatus.NOT_FOUND);

        }
    }
}