package com.abiha.springboot.bootcampproject.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginLogoutController {

    @GetMapping("/logout/successfully")
    public String logout(){
        return "Logged out Successfully!";
    }
}
