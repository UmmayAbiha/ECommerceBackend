
package com.abiha.springboot.bootcampproject.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CartPK.class)
public class Cart implements Serializable {

    private int quantity;
    private boolean isWishlistItem = false;

    @Id
    @ManyToOne
    private ProductVariation productVariation;

    @Id
   // @OneToOne
    @ManyToOne
    private Customer customer;

}








