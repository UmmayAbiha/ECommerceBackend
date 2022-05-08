package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Cart;
import com.abiha.springboot.bootcampproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Long> {

    List<Cart> findAllByCustomer(Customer customer);

    @Transactional
    @Modifying
    void deleteByCustomer(Customer customer);

    @Transactional
    @Modifying
    @Query("delete from Cart")
    void deleteByCustomerAndProductVariationIn(@Param("id1") Long id1,@Param("id2") Long id2);

    @Query(value = "select * from cart where customer_user_id=:uid and product_variation_id=:vid",nativeQuery = true)
    Cart findByCustomerVarIds(@Param("uid") Long id1, @Param("vid") Long id2);

    @Query(value = "select * from cart where customer_user_id=:uid and product_variation_id in (:vids)",nativeQuery = true)
    List<Cart> findByCustomerAndVarIds(@Param("uid") Long id, @Param("vids") List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "delete from cart where customer_user_id=:uid and product_variation_id in (:vids)",nativeQuery = true)
    void deleteByCustomerAndVarIds(@Param("uid") Long id, @Param("vids") List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "delete from cart where customer_user_id=:uid and product_variation_id=:vid",nativeQuery = true)
    void deleteByCustomerVarIds(@Param("uid") Long id1, @Param("vid") Long id2);

}


