package com.abiha.springboot.bootcampproject.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductReviewPK.class)
public class ProductReview implements Serializable {

    private String reviews;

    private String rating;


    @Id
    @ManyToOne
    private Customer customer;

    @Id
    @ManyToOne
    private Product products;

}