package com.abiha.springboot.bootcampproject.event;

import com.abiha.springboot.bootcampproject.entities.Role;
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
            user.setPassword(new BCryptPasswordEncoder().encode("Abiha@12"));


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
        }
    }
}