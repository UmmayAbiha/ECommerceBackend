package com.abiha.springboot.bootcampproject.event;

import com.abiha.springboot.bootcampproject.constants.Constants;
import com.abiha.springboot.bootcampproject.entities.*;
import com.abiha.springboot.bootcampproject.repos.*;
import com.abiha.springboot.bootcampproject.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @Autowired
    ProductVariationRepo productVariationRepo;


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


            user1.setIsActive(Boolean.TRUE);
            user1.setIsExpired(Boolean.FALSE);
            user1.setIsLocked(Boolean.FALSE);
            user1.setIsDeleted(Boolean.FALSE);
            user1.setInvalidAttemptCount(0);
            user1.setPasswordUpdateDate(new Date());

            users.add(user1);
            role.setUser(users);


            roles.add(role);
            user1.setRoles(roles);

            System.out.println("Hello!");
            userRepo.save(user1);
            users.clear();
            roles.clear();


            // Customers
            Role role2 = roleRepo.findByAuthority(Constants.ROLE_CUSTOMER);

            User user2 = new User("John", ".", "Doug", "ummayabiha04@gmail.com");
            Customer customer1 = new Customer("700987536", user2);
            user2.setPassword(passwordEncoder.encode("Abiha@12"));
            user2.setIsActive(true);
            user2.setIsLocked(false);
            user2.setCustomer(customer1);

            users.add(user2);
            role2.setUser(users);

            roles.add(role2);
            user2.setRoles(roles);

            userRepo.save(user2);
            users.clear();
            roles.clear();


            Role role3 = roleRepo.findByAuthority(Constants.ROLE_CUSTOMER);
            User user3 = new User("Ashtyn", ".", "Hill", "abihaumma@student.iul.ac.in");
            Customer customer2 = new Customer("700987536", user3);
            user3.setPassword(passwordEncoder.encode("Abiha@12"));
            user3.setIsActive(true);
            user3.setIsLocked(false);
            user3.setCustomer(customer2);

            users.add(user3);
            role3.setUser(users);

            roles.add(role3);
            user3.setRoles(roles);

            userRepo.save(user3);
            users.clear();
            roles.clear();


            //Sellers
            Role role4 = roleRepo.findByAuthority(Constants.ROLE_SELLER);
            User user4 = new User("Ella", ".", "Fung", "abihaumma+1@student.iul.ac.in");
            Seller seller1 = new Seller(user4, "06BZAHM6385P6Z2", "Wipro", "4568793214");
            user4.setPassword(passwordEncoder.encode("Abiha@12"));
            user4.setIsActive(true);
            user4.setIsLocked(false);
            user4.setSeller(seller1);

            users.add(user4);
            role4.setUser(users);

            roles.add(role4);
            user4.setRoles(roles);

            userRepo.save(user4);
            users.clear();
            roles.clear();


            Role role5 = roleRepo.findByAuthority(Constants.ROLE_SELLER);
            User user5 = new User("Aiza", ".", "Aslam", "abihaumma+2@student.iul.ac.in");
            Seller seller2 = new Seller(user5, "06BZAHM6385P9W4", "TTN", "9875421479");
            user5.setPassword(passwordEncoder.encode("Abiha@12"));
            user5.setIsActive(true);
            user5.setIsLocked(false);
            user5.setSeller(seller2);

            users.add(user5);
            role5.setUser(users);

            roles.add(role5);
            user5.setRoles(roles);

            userRepo.save(user5);
            users.clear();
            roles.clear();


            //Saving the categories
            Category electronics = new Category("Electronics");
            electronics.setParentCategory(null);
            categoryRepo.save(electronics);

            //LeafCategory of electronics
            Category phone = new Category("Mobiles");
            phone.setParentCategory(electronics);
            categoryRepo.save(phone);

            Category fashion = new Category("Fashion");
            categoryRepo.save(fashion);


            Category shoes = new Category("Shoes");
            shoes.setParentCategory(fashion);
            categoryRepo.save(shoes);

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
            product.setCategory(phone);
            product.setIsActive(true);
            productRepo.save(product);

            Product product1 = new Product("iphone 11 pro", "6.1-inch Liquid Retina HD LCD display\n", "Apple", false, false);
            product1.setSeller(seller1);
            product1.setCategory(phone);
            product1.setIsActive(true);
            productRepo.save(product1);


            Product product2 = new Product("Formal Shirt", "Printed Slim Fit Formal Shirt", "UCB", false, false);
            product2.setSeller(seller2);
            product2.setCategory(shirts);
            product2.setIsActive(true);
            productRepo.save(product2);

            Product product3 = new Product("Casual Shirt", "Regular Fit Casual Shirt", "LP", false, false);
            product3.setSeller(seller2);
            product3.setCategory(shirts);
            product3.setIsActive(true);
            productRepo.save(product3);


            // product variation

            // iphone 11 -->
            ProductVariation variation = new ProductVariation();
            variation.setProduct(product);
            variation.setPrice(100000.0f);
            variation.setQuantityAvailable(5);
            productVariationRepo.save(variation);

            //apple variation->iphone11 pro
            ProductVariation productVariation = new ProductVariation();
            productVariation.setProduct(product1);
            productVariation.setPrice(10000.0f);
            productVariation.setQuantityAvailable(10);
            productVariationRepo.save(productVariation);

            ProductVariation productVariation2 = new ProductVariation();
            productVariation2.setProduct(product1);
            productVariation2.setPrice(112200.0f);
            productVariation2.setQuantityAvailable(21);
            productVariationRepo.save(productVariation2);
        }
    }
}