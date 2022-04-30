package com.abiha.springboot.bootcampproject.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductReviewPK {

    private Long customer;

    private Long product;
}
