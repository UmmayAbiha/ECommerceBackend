package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    private String description;

    @JsonIgnore
    private Boolean isCancellable= false;

    @JsonIgnore
    private Boolean isReturnable=false;

    @NotNull
    private String brand;

    @JsonIgnore
    private Boolean isActive=false;

    @JsonIgnore
    private Boolean isDeleted=false;

   // private Boolean outOfStock = false;

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


    public Product(String productName, String description,String brand, boolean isCancellable, boolean isReturnable) {
        this.name = productName;
        this.description = description;
        this.brand = brand;
        this.isCancellable = isCancellable;
        this.isReturnable = isReturnable;
    }
}