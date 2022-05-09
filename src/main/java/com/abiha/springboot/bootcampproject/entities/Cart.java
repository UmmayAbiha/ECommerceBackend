
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
public class Cart extends AuditingInfo implements Serializable{

    private int quantity;
    private boolean isWishlistItem = false;

    @Id
    @ManyToOne
    private ProductVariation productVariation;

    @Id
    @ManyToOne
    private Customer customer;

}








