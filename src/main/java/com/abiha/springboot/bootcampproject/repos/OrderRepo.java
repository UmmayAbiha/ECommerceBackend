
package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Long> {
}


