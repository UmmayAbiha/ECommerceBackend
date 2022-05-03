package com.abiha.springboot.bootcampproject.dto;

import com.abiha.springboot.bootcampproject.entities.Cart;
import lombok.Data;

@Data
public class CartDto {

    private Cart cart;
    private boolean outOfStock = false;

}
