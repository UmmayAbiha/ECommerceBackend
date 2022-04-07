package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.dto.CustomerDto;
import com.abiha.springboot.bootcampproject.dto.SellerDto;
import com.abiha.springboot.bootcampproject.model.Customer;
import com.abiha.springboot.bootcampproject.model.Seller;
import com.abiha.springboot.bootcampproject.services.CustomerService;
import com.abiha.springboot.bootcampproject.services.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getCustomersList();
    }

    @PostMapping("/customerRegister")
    public ResponseEntity<Object> customerRegistration(@Valid @RequestBody CustomerDto customerDto)
    {

        Customer customerAdding = customerService.registerCustomer(customerDto);

        return new ResponseEntity<>("Customer Registered", HttpStatus.CREATED);
    }

    @PostMapping("/sellerRegister")
    public ResponseEntity<Object> sellerRegistration(@Valid @RequestBody SellerDto sellerDto)
    {

        Seller sellerAdding = sellerService.registerSeller(sellerDto);

        return new ResponseEntity<>("Seller Registered", HttpStatus.CREATED);
    }

}

