package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.dto.CartDto;
import com.abiha.springboot.bootcampproject.entities.Cart;
import com.abiha.springboot.bootcampproject.entities.Product;
import com.abiha.springboot.bootcampproject.entities.ProductVariation;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.exception.ProductNotFoundException;
import com.abiha.springboot.bootcampproject.exception.ProductOutOfStockException;
import com.abiha.springboot.bootcampproject.exception.ProductVariationNotFoundException;
import com.abiha.springboot.bootcampproject.exception.UserNotFoundException;
import com.abiha.springboot.bootcampproject.repos.CartRepo;
import com.abiha.springboot.bootcampproject.repos.ProductVariationRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import com.abiha.springboot.bootcampproject.utils.SecurityContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProductVariationRepo productVariationRepo;


    public String addProduct(Long productVarId, int quantity) {

        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        }
        else {
            Cart cart = new Cart();
            ProductVariation productVariation = productVariationRepo.findById(productVarId).orElse(null);
            if (productVariation!=null) {
                if (productVariation.getIsActive() && quantity>0 && !productVariation.getProduct().getIsDeleted()) {
                    Product product = productVariation.getProduct();
                    cart.setCustomer(user.getCustomer());
                    cart.setQuantity(quantity);
                    cart.setProductVariation(productVariation);
                    cartRepo.save(cart);
                }
                else{
                    StringBuilder str = new StringBuilder();
                    if(!productVariation.getIsActive()){
                        str.append("Product Variation not active\n");
                    }
                    if(quantity<=0){
                        str.append("Quantity should be greater than zero!\n");
                    }
                    if(productVariation.getProduct().getIsDeleted()){
                        str.append("Deleted Product!");
                    }
                    throw new ValidationException(String.valueOf(str));
                }
            } else {
                throw new ProductVariationNotFoundException("Product Variation Not Found");
            }
        }
        return "Product added to cart";
    }


    public List<CartDto> viewingCart(){
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User Not Found");
        }
        List<Cart> cartList = cartRepo.findAllProducts();
        List<CartDto> list = new ArrayList<>();
        CartDto cartDto = new CartDto();
        for (Cart cart:cartList ){
            if(cart.getProductVariation().getQuantityAvailable()==0)
                cartDto.setOutOfStock(true);
            else {
                cartDto.setOutOfStock(false);
            }
            cartDto.setCart(cart);
            list.add(cartDto);
        }
        return list;
    }


    public String deleteProduct(Long id) {

        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            ProductVariation productVariation = productVariationRepo.findById(id).orElse(null);
            if (productVariation != null) {
                if (cartRepo.findByProductVariationId(id)!=null) {
                    cartRepo.deleteByProductVariationId(id);
                    return "Product deleted from cart!";
                } else
                    throw new ProductNotFoundException("Product for this id is not found");
            } else {
                throw new ProductVariationNotFoundException("Product variation not found!");
            }
        }
    }



    // update query??
    //org.springframework.orm.jpa.JpaSystemException: No part of a composite identifier may be null
    //org.hibernate.HibernateException: No part of a composite identifier may be null
    public void updateProduct(Long id, int quan) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            Cart cart = new Cart();
            ProductVariation productVariation = productVariationRepo.findById(id).orElse(null);
            if (productVariation != null) {
                if (productVariation.getIsActive() && !productVariation.getProduct().getIsDeleted() && quan >= 0 && cartRepo.findByProductVariationId(id)!=null) {
                    cart.setQuantity(quan);
                    // yaha pe baaki fields ka kya??
                    //hme toh sirf ye update krna tha
                    cartRepo.save(cart);
                } else {
                    StringBuilder str = new StringBuilder();
                    if (!productVariation.getIsActive()) {
                        str.append("Product Variation not active\n");
                    }
                    if (quan == 0) {
                        str.append("Product removed from cart!\n");
                        cartRepo.deleteByProductVariationId(id);
                        //
                    }
                    if (productVariation.getProduct().getIsDeleted()) {
                        str.append("Product is a deleted product!");
                    }
                    if(cartRepo.findByProductVariationId(id) == null){
                        str.append("Product doesn't exists in user's cart!");
                    }
                    throw new ValidationException(String.valueOf(str));
                }
            } else {
                throw new ProductVariationNotFoundException("Product Variation Not Found");
            }
        }
    }


    public String emptyCart(){
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new UserNotFoundException("User Not Found");
        }
        else {
            cartRepo.deleteAll();
            return "Cart Emptied!";
        }
    }
}


