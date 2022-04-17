
package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private boolean isWishlistItem;

    @OneToMany
    @JoinColumn(name = "cart_id")
    private List<ProductVariation> productVariations;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isWishlistItem() {
        return isWishlistItem;
    }

    public void setWishlistItem(boolean wishlistItem) {
        isWishlistItem = wishlistItem;
    }
}






