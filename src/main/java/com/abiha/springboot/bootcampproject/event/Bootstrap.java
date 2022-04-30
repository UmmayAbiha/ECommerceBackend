package com.abiha.springboot.bootcampproject.event;

import com.abiha.springboot.bootcampproject.entities.Customer;
import com.abiha.springboot.bootcampproject.entities.Role;
import com.abiha.springboot.bootcampproject.entities.Seller;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.repos.RoleRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    public void createRoles()
    {

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(roleRepo.findByName("ROLE_ADMIN")== null) {

            Role role1 = new Role();
            Role role2 = new Role();
            Role role3 = new Role();

            role1.setId(1l);
            role1.setName("ROLE_ADMIN");

            role2.setId(2l);
            role2.setName("ROLE_SELLER");

            role3.setId(3l);
            role3.setName("ROLE_CUSTOMER");

            roleRepo.save(role1);
            roleRepo.save(role2);
            roleRepo.save(role3);
        }



        if(Objects.isNull(userRepo.findByEmail("ummay.abiha@tothenew.com"))) {

            Role role = roleRepo.findById(1l).get();
            Set<User> users = new HashSet<>();

            User user = new User();
            user.setId(1l);
            user.setEmail("ummay.abiha@tothenew.com");
            user.setFirstName("Ummay");
            user.setLastName("Abiha");
            user.setPassword(passwordEncoder.encode("Abiha@12"));


            users.add(user);
            role.setUser(users);

            Set<Role> roles = new HashSet<>();

            roles.add(role);
            user.setRoles(roles);

            user.setActive(Boolean.TRUE);
            user.setExpired(Boolean.FALSE);
            user.setLocked(Boolean.FALSE);
            user.setDeleted(Boolean.FALSE);
            user.setInvalidAttemptCount(0);
            user.setPasswordUpdateDate(new Date());

            System.out.println("Hello!");
            userRepo.save(user);
            users.remove(user);
            roles.clear();


            Role role2 = roleRepo.findByName("ROLE_CUSTOMER");

            User user2 = new User("John", ".", "Doug", "ummayabiha04@gmail.com");
            Customer customer1 = new Customer("700987536", user2);
            user2.setPassword(passwordEncoder.encode("Abiha@12"));
            user2.setActive(true);
            user2.setLocked(false);
            user2.setCustomer(customer1);

            users.add(user2);
            role2.setUser(users);

            roles.add(role2);
            user2.setRoles(roles);

            userRepo.save(user2);
            users.remove(user2);
            roles.clear();



            Role role3 = roleRepo.findByName("ROLE_CUSTOMER");
            User user3 = new User("Ashtyn", ".", "Hill", "abihaumma@student.iul.ac.in");
            Customer customer2 = new Customer("700987536", user3);
            user3.setPassword(passwordEncoder.encode("Abiha@12"));
            user3.setActive(true);
            user3.setLocked(false);
            user3.setCustomer(customer2);

            users.add(user3);
            role3.setUser(users);

            roles.add(role3);
            user3.setRoles(roles);

            userRepo.save(user3);
            users.remove(user3);
            roles.clear();



            //Sellers
            Role role4 = roleRepo.findByName("ROLE_SELLER");
            User user4 = new User("Ella", ".", "Fung", "abihaumma+1@student.iul.ac.in");
            Seller seller1 = new Seller(user4, "06BZAHM6385P6Z2", "Wipro", "4568793214");
            user4.setPassword(passwordEncoder.encode("Abiha@12"));
            user4.setActive(true);
            user4.setLocked(false);
            user4.setSeller(seller1);

            users.add(user4);
            role4.setUser(users);

            roles.add(role4);
            user4.setRoles(roles);

            userRepo.save(user4);
            users.remove(user4);
            roles.clear();



            Role role5 = roleRepo.findByName("ROLE_SELLER");
            User user5 = new User("Aiza", ".", "Aslam", "abihaumma+2@student.iul.ac.in");
            Seller seller2 = new Seller(user5, "06BZAHM6385P9W4", "TTN", "9875421479");
            user5.setPassword(passwordEncoder.encode("Abiha@12"));
            user5.setActive(true);
            user5.setLocked(false);
            user5.setSeller(seller2);

            users.add(user5);
            role5.setUser(users);

            roles.add(role5);
            user5.setRoles(roles);

            userRepo.save(user5);
            users.remove(user5);
            roles.clear();


        }
        }
    }
