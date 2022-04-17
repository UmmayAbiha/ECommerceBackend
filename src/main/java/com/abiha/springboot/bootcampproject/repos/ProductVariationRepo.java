package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariationRepo extends JpaRepository<ProductVariation,Long> {
}
