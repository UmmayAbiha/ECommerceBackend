
package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderStatusRepo extends JpaRepository<OrderStatus,Long> {

    @Query(value = "select * from order_status where order_product_id=:id",nativeQuery = true)
    OrderStatus findByOrderProductId(@Param("id") Long id);
}


