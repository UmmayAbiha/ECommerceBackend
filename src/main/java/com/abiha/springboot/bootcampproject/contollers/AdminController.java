package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import com.abiha.springboot.bootcampproject.services.AdminService;
import com.abiha.springboot.bootcampproject.services.CustomerService;
import com.abiha.springboot.bootcampproject.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/customers")
    public List<Map<String,Object>> getAllCustomers() {
        return adminService.getCustomersList();
    }

    @GetMapping("/sellers")
    public List<Map<String, Object>> getAllSellers(){
        return adminService.getSellersList();
    }

    //@Transactional
    @PatchMapping(value = "/activateUser/{id}")
    public ResponseEntity<Object> activateUser(@PathVariable("id") Long id){
        System.out.println("ID: "+id);
        User customer=userRepo.findById(id).get();
        if(customer==null){
            System.out.println("Not found");
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }

        else if(customer.isActive){
            System.out.println("Found");
            return new ResponseEntity<>("Already in activated state",HttpStatus.OK);
        }
        else {
            System.out.println("found");
            adminService.activate(customer);
            return new ResponseEntity<>("Account is activated", HttpStatus.CREATED);

        }
    }

    //@Transactional
    @PatchMapping(value = "/deactivateUser/{id}")
    public ResponseEntity<Object> deactivateUser(@PathVariable("id") Long id) {
        System.out.println("ID: " + id);
        User customer = userRepo.findById(id).get();
        if (customer == null) {
            System.out.println("Not found");
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        else if (id==1)
            return new ResponseEntity("You Don't Deactivate the Main admin user !!!! ", HttpStatus.OK);
        else if(customer.isActive){
            adminService.deActivate(customer);
            return new ResponseEntity<>("Account is de-activated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(" Your account was already de-activated", HttpStatus.OK);
        }
    }

}


