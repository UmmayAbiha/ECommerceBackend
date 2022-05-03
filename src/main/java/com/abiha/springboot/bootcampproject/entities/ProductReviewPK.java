package com.abiha.springboot.bootcampproject.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductReviewPK implements Serializable {

    private Long customer;

    private Long products;
}
