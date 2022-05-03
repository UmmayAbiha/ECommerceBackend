package com.abiha.springboot.bootcampproject.services;


import com.abiha.springboot.bootcampproject.entities.Customer;
import com.abiha.springboot.bootcampproject.entities.Seller;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.CustomerRepo;
import com.abiha.springboot.bootcampproject.repos.SellerRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;


    public List<Map<String, Object>> getCustomersList() {

        List<Customer> customerList = customerRepo.findAll();

        List<Map<String,Object>> customers = new ArrayList<>();
        List<User> users = userRepo.findAll(PageRequest.of(0, 10, Sort.by("id").and(Sort.by("email")))).getContent();
        for(Customer customer: customerList) {

            for (User user : users) {
                if(customer.getUserId()==user.getId()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("id", user.getId());
                    map.put("FullName", user.getFirstName() + user.getMiddleName() + user.getLastName());
                    map.put("Email", user.getEmail());
                    map.put("isActive", user.isActive);
                    customers.add(map);
                }
            }
        }
        return customers;

    }


    public List<Map<String, Object>> getSellersList() {

        List<Seller> sellerList = sellerRepo.findAll();

        List<Map<String,Object>> sellers = new ArrayList<>();
        List<User> users = userRepo.findAll(PageRequest.of(0, 10, Sort.by("id").and(Sort.by("email")))).getContent();
        for(Seller seller: sellerList) {

            for (User user : users) {
                if(seller.getId()==user.getId()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("id", user.getId());
                    map.put("FullName", user.getFirstName() + user.getLastName());
                    map.put("Email", user.getEmail());
                    map.put("isActive", user.isActive);
                    map.put("Company Name", seller.getCompanyName());
                    map.put("Company Contact", seller.getCompanyContact());
                    sellers.add(map);
                }
            }
        }
        return sellers;

    }

    public User activate(User user) {
        user.setIsActive(true);
        user.setIsLocked(false);
        userRepo.save(user);
        String message = "Congratulations!!\nYour account is being activated. We hope you enjoy our service";

        emailService.sendSimpleMailMessage(user.getEmail(),"Account Activation!",message );
        return user;
    }


    public User deActivate(User user) {
        user.setIsActive(false);
        userRepo.save(user);
        String message = "Your account is De-Activated :( \nFeel free to connect if you want to activate you account.";

        emailService.sendSimpleMailMessage(user.getEmail(), "Account De-activation" ,message);
        return user;

    }
}
