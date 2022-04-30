package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    private String description;

    private Boolean isCancellable= false;

    private Boolean isReturnable=false;

    private String brand;

    private Boolean isActive=false;

    private Boolean isDeleted=false;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name= "seller_user_id",referencedColumnName = "user_id",nullable = false)
    private Seller seller;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId",referencedColumnName = "id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<ProductVariation> productVariations;



    public Product() {
    }

    public Product(String productName, String description,String brand, boolean isCancellable, boolean isReturnable) {
        this.name = productName;
        this.description = description;
        this.brand = brand;
        this.isCancellable = isCancellable;
        this.isReturnable = isReturnable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsCancellable() {
        return isCancellable;
    }

    public void setIsCancellable(Boolean isCancellable) {
        this.isCancellable = isCancellable;
    }

    public Boolean getIsReturnable() {
        return isReturnable;
    }

    public void setIsReturnable(Boolean isReturnable) {
        this.isReturnable = isReturnable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public com.abiha.springboot.bootcampproject.entities.Category getCategory() {
        return category;
    }

    public void setCategory(com.abiha.springboot.bootcampproject.entities.Category category) {
        this.category = category;
    }

    public Set<ProductVariation> getProductVariations() {
        return productVariations;
    }

    public void setProductVariations(Set<ProductVariation> productVariations) {
        this.productVariations = productVariations;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Boolean getCancellable() {
        return isCancellable;
    }

    public void setCancellable(Boolean cancellable) {
        isCancellable = cancellable;
    }

    public Boolean getReturnable() {
        return isReturnable;
    }

    public void setReturnable(Boolean returnable) {
        isReturnable = returnable;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}