/*package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.entities.Cart;
import com.abiha.springboot.bootcampproject.entities.Product;
import com.abiha.springboot.bootcampproject.entities.ProductVariation;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.exception.UserNotFoundException;
import com.abiha.springboot.bootcampproject.repos.CartRepo;
import com.abiha.springboot.bootcampproject.repos.ProductVariationRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import com.abiha.springboot.bootcampproject.utils.SecurityContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProductVariationRepo productVariationRepo;


    public String addProductInCart(Long productVarId,int quantity){

        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("Not Found");
        }

        /// confuseddd
        else{
            Cart cart = new Cart();
          ProductVariation productVariation = productVariationRepo.findById(productVarId).get();
            if(productVariation.getIsActive()==true && quantity>0 && productVariation.getProduct().getIsDeleted()==false){
                Product product = productVariation.getProduct();

                cartRepo.save(product);
                return "Product added to cart";
            }
            else {
                throw new ValidationException("Validation failed");
            }
        }
    }


    public List<Cart> viewingCart(){

        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("Not Found");
        }
        List<Cart> cartList = cartRepo.findAllProducts();
        return cartList;
    }


    public ResponseEntity<Object> deleteCartProduct(Long id) {

        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("Not Found");
        }
        if(cartRepo.findById(id).isPresent()){
            Cart cart= cartRepo.findById(id).get();
            cartRepo.deleteById(id);
            return new ResponseEntity<>("Product deleted from cart",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product for this id is not present",HttpStatus.BAD_REQUEST);
    }
}

 */
