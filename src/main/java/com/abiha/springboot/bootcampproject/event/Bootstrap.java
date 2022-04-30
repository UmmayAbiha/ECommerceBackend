package com.abiha.springboot.bootcampproject.event;

import com.abiha.springboot.bootcampproject.constants.Constants;
import com.abiha.springboot.bootcampproject.entities.*;
import com.abiha.springboot.bootcampproject.repos.CategoryRepo;
import com.abiha.springboot.bootcampproject.repos.ProductRepo;
import com.abiha.springboot.bootcampproject.repos.RoleRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import com.abiha.springboot.bootcampproject.services.RoleService;
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
    RoleService roleService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ProductRepo productRepo;


    public void createRoles()
    {

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (roleRepo.findByAuthority(Constants.ROLE_ADMIN) == null) {

            roleService.save(Constants.ROLE_ADMIN);
        }
        if (roleRepo.findByAuthority(Constants.ROLE_SELLER) == null) {
            roleService.save(Constants.ROLE_SELLER);

        }
        if (roleRepo.findByAuthority(Constants.ROLE_CUSTOMER) == null) {
            roleService.save(Constants.ROLE_CUSTOMER);
        }


        Seller seller1 = null;
        if (Objects.isNull(userRepo.findByEmail("ummay.abiha@tothenew.com"))) {
            Role role = roleRepo.findByAuthority(Constants.ROLE_ADMIN);
            Set<Role> roles = new HashSet<>();
            Set<User> users = new HashSet<>();

            //Admin

            User user1 = new User();
            //  user1.setId(1l);
            user1.setEmail("ummay.abiha@tothenew.com");
            user1.setFirstName("Ummay");
            user1.setLastName("Abiha");
            user1.setPassword(passwordEncoder.encode("Abiha@12"));

            user1.setActive(Boolean.TRUE);
            user1.setExpired(Boolean.FALSE);
            user1.setLocked(Boolean.FALSE);
            user1.setDeleted(Boolean.FALSE);
            user1.setInvalidAttemptCount(0);
            user1.setPasswordUpdateDate(new Date());

            users.add(user1);
            role.setUser(users);


            roles.add(role);
            user1.setRoles(roles);

            System.out.println("Hello!");
            userRepo.save(user1);


            // Customers
            Role role2 = roleRepo.findByAuthority(Constants.ROLE_CUSTOMER);

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


            Role role3 = roleRepo.findByAuthority(Constants.ROLE_CUSTOMER);
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


            //Sellers
            Role role4 = roleRepo.findByAuthority(Constants.ROLE_CUSTOMER);
            User user4 = new User("Ella", ".", "Fung", "abihaumma@student.iul.ac.in");
            seller1 = new Seller(user4, "06BZAHM6385P6Z2", "Wipro", "4568793214");
            user4.setPassword(passwordEncoder.encode("Abiha@12"));
            user4.setActive(true);
            user4.setLocked(false);
            user4.setSeller(seller1);

            users.add(user4);
            role4.setUser(users);

            roles.add(role4);
            user4.setRoles(roles);

            userRepo.save(user4);


            Role role5 = roleRepo.findByAuthority(Constants.ROLE_CUSTOMER);
            User user5 = new User("Aiza", ".", "Aslam", "abihaumma@student.iul.ac.in");
            Seller seller2 = new Seller(user4, "06BZAHM6385P9W4", "TTN", "9875421479");
            user5.setPassword(passwordEncoder.encode("Abiha@12"));
            user5.setActive(true);
            user5.setLocked(false);
            user5.setSeller(seller2);

            users.add(user5);
            role5.setUser(users);

            roles.add(role5);
            user5.setRoles(roles);

            userRepo.save(user5);

        }


        //Saving the categories
        Category electronics = new Category("Electronics");
        electronics.setParentCategory(null);
        categoryRepo.save(electronics);

        //LeafCategory of electronics
        Category phones = new Category("Mobiles");
        phones.setParentCategory(electronics);
        categoryRepo.save(phones);

        Category fashion = new Category("Fashion");
        categoryRepo.save(fashion);

        // leaf category set???

        Category shoes = new Category("Shoes");
        shoes.setParentCategory(fashion);
        categoryRepo.save(shoes);

        // is this ookk??

        Category boots = new Category("Boots");
        boots.setParentCategory(shoes);
        categoryRepo.save(boots);


        Category shirts = new Category("Shirts");
        shirts.setParentCategory(fashion);
        categoryRepo.save(shirts);

        Category jeans = new Category("Jeans");
        jeans.setParentCategory(fashion);
        categoryRepo.save(jeans);

        Category toys = new Category("Toys");
        categoryRepo.save(toys);

        Category footwear = new Category("Footwear");
        categoryRepo.save(footwear);

        Category accessories = new Category("Accessories");
        categoryRepo.save(accessories);

        Category grocery = new Category("Grocery");
        categoryRepo.save(grocery);

        Category watches = new Category("Watches");
        categoryRepo.save(watches);

        Category books = new Category("Books");
        categoryRepo.save(books);


        //saving the products
        Product product = new Product("iphone 11", "Double camera System", "Apple", false, false);
        product.setSeller(seller1);
        product.setCategory(phones);
        product.setIsActive(true);
        productRepo.save(product);

        Product product1 = new Product("iphone 11 pro", "6.1-inch Liquid Retina HD LCD display\n", "Apple", false, false);
        product1.setSeller(seller1);
        product1.setCategory(phones);
        product1.setIsActive(true);
        productRepo.save(product1);


        Product product2 = new Product("Formal Shirt", "Printed Slim Fit Formal Shirt", "UCB", false, false);
        product2.setSeller(seller1);
        product2.setCategory(shirts);
        product2.setIsActive(true);
        productRepo.save(product2);

        Product product3 = new Product("Casual Shirt", "Regular Fit Casual Shirt", "LP", false, false);
        product3.setSeller(seller1);
        product3.setCategory(shirts);
        product3.setIsActive(true);
        productRepo.save(product3);


    }
}