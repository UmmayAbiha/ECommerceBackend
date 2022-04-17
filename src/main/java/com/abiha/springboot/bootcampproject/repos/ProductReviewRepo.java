package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepo extends JpaRepository<ProductReview,Long> {
}
