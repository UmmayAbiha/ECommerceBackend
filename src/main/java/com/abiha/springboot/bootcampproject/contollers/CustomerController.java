package com.abiha.springboot.bootcampproject.contollers;


import com.abiha.springboot.bootcampproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerController {

    @Autowired
    private CustomerService customerService;
}
