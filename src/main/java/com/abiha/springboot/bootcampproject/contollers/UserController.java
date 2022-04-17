package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/viewProfile")
    public ResponseEntity<Object> viewUserProfile() {
        return userService.viewUserDetails();
    }
}