
package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepo extends JpaRepository<OrderStatus,Long> {
}


