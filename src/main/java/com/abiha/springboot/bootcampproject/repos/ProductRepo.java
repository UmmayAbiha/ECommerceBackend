package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
