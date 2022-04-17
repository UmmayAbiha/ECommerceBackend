
package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepo extends JpaRepository<OrderProduct,Long> {
}

