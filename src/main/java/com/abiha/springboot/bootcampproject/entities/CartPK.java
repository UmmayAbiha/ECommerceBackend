
package com.abiha.springboot.bootcampproject.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class CartPK implements Serializable {

    private Long customer;

    private Long productVariation;
}


