package com.abiha.springboot.bootcampproject.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@IdClass(ProductReviewPK.class)
public class ProductReview {

    private String reviews;

    private String rating;


    @Id
    @OneToOne
    private Customer customer;

    @Id
    @ManyToOne
    private Product products;

}