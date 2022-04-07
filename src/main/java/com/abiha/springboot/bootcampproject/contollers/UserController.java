package com.abiha.springboot.bootcampproject.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    /*
    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public User register(UserDto userDto) {

        // crud repo se aata tha but ab kaise hoga?
        userService.save(userDto);
        return "login";
    }
    /*

    @GetMapping("/showReg")
    public String showRegistrationPage() {
        return "registerUser";

    }


    @PostMapping("/login")
    public String login(String email,String password){
       boolean loginResponse = securityService.login(email,password);
       if(loginResponse){
           return "index";
       }
       else
           return "login";
    }
    */
}