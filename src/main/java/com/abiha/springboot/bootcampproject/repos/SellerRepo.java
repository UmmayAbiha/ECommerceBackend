package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller,Integer> {
}
