/*package com.abiha.springboot.bootcampproject.contollers;

import com.abiha.springboot.bootcampproject.entities.Cart;
import com.abiha.springboot.bootcampproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-product")
    public String addCartProduct(@Valid @PathVariable Long id, @RequestParam int quan){

        return cartService.addProductInCart(id,quan);

    }

    @GetMapping("/vewCart")
    public List<Cart> viewCartDetails(){
        return cartService.viewingCart();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Object> deleteProductInCart(@PathVariable Long id){
        return cartService.deleteCartProduct(id);

    }

    @DeleteMapping("/emptyCart")
    public
}

 */
