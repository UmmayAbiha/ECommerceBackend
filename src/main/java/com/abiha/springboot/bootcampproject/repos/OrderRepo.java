
package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Customer;
import com.abiha.springboot.bootcampproject.entities.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Long> {

    List<Orders> findAllByCustomer(@Param("customer") Customer customer, Pageable pageable);

    @Query(value = "select * from orders o left join order_product op on o.id = op.order_id " +
            "left join product_variation pv on op.product_variation_id = pv.id " +
            "left join product p on pv.product_id = p.id where seller_user_id =:id", nativeQuery = true)
    List<Orders> findAllBySeller(@Param("id") Long id, Pageable pageable);

}


