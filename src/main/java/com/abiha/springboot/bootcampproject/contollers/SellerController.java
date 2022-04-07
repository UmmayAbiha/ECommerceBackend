package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerController {

    @Autowired
    private SellerService sellerService;
}
