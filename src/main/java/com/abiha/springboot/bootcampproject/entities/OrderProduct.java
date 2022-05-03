
package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private Double price;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Orders order;

    @OneToOne
    @JoinColumn(name ="product_variation_id",referencedColumnName = "id", nullable= false)
    private ProductVariation productVariation;
}




