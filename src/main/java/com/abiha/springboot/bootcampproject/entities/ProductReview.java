package com.abiha.springboot.bootcampproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ProductReview {
    @Id
    private Long id;

    private String reviews;

    private String rating;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name="customerUserId")
    @MapsId
    private Customer customer;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="productId")
    @MapsId
    private Product product;

    public ProductReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public com.abiha.springboot.bootcampproject.entities.Product getProduct() {
        return product;
    }

    public void setProduct(com.abiha.springboot.bootcampproject.entities.Product product) {
        this.product = product;
    }


}