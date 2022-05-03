package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepo extends CrudRepository<Cart,Long> {

    @Query(value = "select * from cart",nativeQuery = true)
    List<Cart> findAllProducts();

    @Query(value = "select * from cart where product_variation_id=:id",nativeQuery = true)
    Cart findByProductVariationId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from cart where product_variation_id=:id",nativeQuery = true)
    void deleteByProductVariationId(@Param("id") Long id);


}


