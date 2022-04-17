package com.abiha.springboot.bootcampproject.contollers;


import com.abiha.springboot.bootcampproject.dto.AddressDto;
import com.abiha.springboot.bootcampproject.dto.CustomerDto;
import com.abiha.springboot.bootcampproject.dto.UserDto;
import com.abiha.springboot.bootcampproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Transactional
    @PatchMapping("/update-customer-profile")
    public ResponseEntity<Object> updatingProfileDetails(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomerProfile(customerDto);
    }

    @PatchMapping("/update-customer-password")
    public ResponseEntity<Object> updatingPassword(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomerPassword(customerDto);
    }

    @GetMapping("/viewAddress")
    public ResponseEntity<Object> viewAddressDetails(){
        return customerService.viewCustomerAddresses();
    }

    @PostMapping("/add-address")
    public ResponseEntity<Object> addAddress(@RequestBody AddressDto addressDto){
        return customerService.addingCustomerAddress(addressDto);
    }

    @PatchMapping("/updateCustomerAddress/{id}")
    public ResponseEntity<Object> updatingCustomerAddress(@Valid @PathVariable Long id, @RequestBody AddressDto dto){
        return customerService.updateCustomerAddress(id,dto);
    }

    @DeleteMapping("/deleteCustomerAddress/{id}")
    public ResponseEntity<Object> deletingCustomerAddress(@PathVariable Long id){
        return customerService.deleteCustomerAddress(id);
    }

}
