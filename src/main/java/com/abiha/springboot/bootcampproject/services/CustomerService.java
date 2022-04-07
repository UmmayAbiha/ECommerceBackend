package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.dto.CustomerDto;
import com.abiha.springboot.bootcampproject.exception.BadRequestException;
import com.abiha.springboot.bootcampproject.model.Customer;
import com.abiha.springboot.bootcampproject.model.Role;
import com.abiha.springboot.bootcampproject.model.Token;
import com.abiha.springboot.bootcampproject.model.User;
import com.abiha.springboot.bootcampproject.repos.CustomerRepo;
import com.abiha.springboot.bootcampproject.repos.RoleRepo;
import com.abiha.springboot.bootcampproject.repos.TokenRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

   // @Autowired
   // private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private TokenRepo tokenRepo;



    public List<Customer> getCustomersList(){
        List<Customer> customers = new ArrayList<>();
        customerRepo.findAll().forEach(customer -> customers.add(customer));

        return customers;
    }


    public Customer registerCustomer(CustomerDto customerDto)
    {

        User user = modelMapper.map(customerDto,User.class);
        Customer customer = modelMapper.map(customerDto,Customer.class);
        customer.setUser(user);


        if(! customerDto.getPassword().equals(customerDto.getConfirmPassword())){
            throw new BadRequestException("Passwords not matched");
        }

        user.setPassword(passwordEncoder.encode(customerDto.getPassword()));

        user.addRole(roleRepo.findByName("role_customer"));

        Token token = new Token(user);
        tokenRepo.save(token);
        String message = "Complete your registration!\n" +
                "Your Token is : "+ token.getActivationToken();
        emailService.sendSimpleMailMessage(user.getEmail(), "Activating Account",message);

        customerRepo.save(customer);
        return customer;

        /*

        Token token1 = new Token(user);
        tokenRepo.save(token1);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+ token1.getActivationToken());


        emailService.sendSimpleMailMessage(mailMessage);

        customerRepo.save(customer);
        return customer;
        */
    }

////////////// WHY AM I NOT ABLE TO USE TOKEN SERVICE FUNCTIONS HERE!!??? /////////////////

    /*

    public User activateUserByToken(Token token){
        User user= modelMapper.map(userRepo.findByEmail(token.getUserEmail()),User.class);
        // validateToken error show kr rha
        Token validateToken = tokenService.validateToken(token);
        if (validateToken == null) {
            throw new BadRequestException("Invalid Token");
        }else if(user==null){
            throw new BadRequestException("User with this email doesn't exist");
        }else{
            user.setActive(true);
            userRepo.save(user);
        }
        return user;
    }

     */




        /*
    public User save(UserDto userDto){
        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles=roleRepo.findAllById(userDto.getRoles());
        user.setRoles(roles);
        userRepo.save(user);
        return  user;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        return authorities;
    }

     */





}