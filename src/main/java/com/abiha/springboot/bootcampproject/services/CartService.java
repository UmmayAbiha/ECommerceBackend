package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.dto.CartDto;
import com.abiha.springboot.bootcampproject.entities.Cart;
import com.abiha.springboot.bootcampproject.entities.Product;
import com.abiha.springboot.bootcampproject.entities.ProductVariation;
import com.abiha.springboot.bootcampproject.entities.User;
import com.abiha.springboot.bootcampproject.exception.*;
import com.abiha.springboot.bootcampproject.repos.CartRepo;
import com.abiha.springboot.bootcampproject.repos.ProductVariationRepo;
import com.abiha.springboot.bootcampproject.repos.UserRepo;
import com.abiha.springboot.bootcampproject.utils.SecurityContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // aval quan and req ka condition check krna hai ya nhi!!!
    public void validateProductVariation(StringBuilder str,ProductVariation productVariation, int quantity){
        if(!productVariation.getIsActive()){
            str.append("Product Variation not active\n");
        }
        if(productVariation.getProduct().getIsDeleted()){
            str.append("Product is a deleted product!\n");
        }
        if(productVariation.getQuantityAvailable()==0){
            str.append("Available quantity is zero, product is out of stock!");
        }
        if(productVariation.getQuantityAvailable()<quantity)
        {
            str.append("Available quantity less than the requested quantity!");
        }
    }


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
                if (productVariation.getIsActive() && quantity>0 && !productVariation.getProduct().getIsDeleted()
                        && productVariation.getQuantityAvailable()>0 && productVariation.getQuantityAvailable() >= quantity) {

                    cart.setCustomer(user.getCustomer());
                    cart.setQuantity(quantity);
                    cart.setProductVariation(productVariation);
                    cartRepo.save(cart);
                }
                else{
                    StringBuilder str = new StringBuilder();
                    validateProductVariation(str,productVariation,quantity);
                    if(quantity<=0){
                        str.append("Quantity should be greater than zero!\n");
                    }
                    throw new ValidationFailedException(String.valueOf(str));
                }
            } else {
                throw new EntityNotFoundException("Product Variation Not Found");
            }
        }
        return "Product added to cart";
    }

    public String updateProduct(Long id, int quantity) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            ProductVariation productVariation = productVariationRepo.findById(id).orElse(null);
            //
            Cart cart = cartRepo.findByCustomerVarIds(user.getId(),id);
            if (productVariation != null) {
                if (productVariation.getIsActive() && quantity>=0 &&!productVariation.getProduct().getIsDeleted() &&
                        productVariation.getQuantityAvailable()>0 && productVariation.getQuantityAvailable() >= quantity && cart!=null) {
                    if (quantity == 0) {
                        cartRepo.deleteByCustomerVarIds(user.getId(),id);
                        return "Quantity is zero,Product removed from cart!";
                    }
                    cart.setQuantity(quantity);
                    cartRepo.save(cart);
                }
                else {
                    StringBuilder str = new StringBuilder();
                    validateProductVariation(str,productVariation,quantity);
                    if(quantity<0){
                        str.append("Quantity should be greater than or equal to zero!\n");
                    }
                    if (cart == null) {
                        str.append("Product doesn't exists in user's cart!");
                    }
                    throw new ValidationFailedException(String.valueOf(str));
                }
            }
            else{
                throw new EntityNotFoundException("Product Variation Not Found");
            }
        }
        return "Cart updated successfully!";
    }


    public List<CartDto> viewingCart(){
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User Not Found");
        }
//        List<Cart> cartList = cartRepo.findAllByCustomerId(user.getId());
        List<Cart> cartList = cartRepo.findAllByCustomer(user.getCustomer());
        List<CartDto> list = new ArrayList<>();
        for (Cart cart:cartList ){
            CartDto cartDto = new CartDto();
            cartDto.setOutOfStock(cart.getProductVariation().getQuantityAvailable() == 0);
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
                if (cartRepo.findByCustomerVarIds(user.getId(),id)!=null) {
                    cartRepo.deleteByCustomerVarIds(user.getId(), id);
                    return "Product deleted from cart!";
                } else
                    throw new EntityNotFoundException("Product for this id is not found");
            } else {
                throw new EntityNotFoundException("Product variation not found!");
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


