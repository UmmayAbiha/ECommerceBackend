
package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Customer;
import com.abiha.springboot.bootcampproject.entities.Orders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Long> {

    @Query(" from Orders  where customer=:customer")
    List<Orders> findAllByCustomer(@Param("customer") Customer customer, Pageable pageable);

}


