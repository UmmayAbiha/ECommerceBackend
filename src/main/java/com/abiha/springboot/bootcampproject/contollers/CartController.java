package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.dto.CartDto;
import com.abiha.springboot.bootcampproject.entities.Cart;
import com.abiha.springboot.bootcampproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-product/{id}")
    public ResponseEntity<String> addCartProduct(@Valid @PathVariable Long id, @RequestParam int quantity){
         String message =cartService.addProduct(id,quantity);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping("/viewCart")
    public List<CartDto> viewCartDetails(){
        return cartService.viewingCart();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProductInCart(@PathVariable Long id){
        return cartService.deleteProduct(id);
    }

    @PatchMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProductInCart(@Valid @PathVariable Long id, @RequestParam int quan){
        cartService.updateProduct(id,quan);
        return new ResponseEntity<>("Product updated in cart!",HttpStatus.OK);
    }

    @DeleteMapping("/emptyCart")
    public String deleteCart(){
        return cartService.emptyCart();
    }
}


