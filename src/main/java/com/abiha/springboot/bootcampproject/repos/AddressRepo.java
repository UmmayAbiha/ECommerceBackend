package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Set;

public interface AddressRepo extends JpaRepository<Address,Long> {
    Set<Address> findByUserId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from address where id =?1",nativeQuery = true)
    void deleteByAddressId(Long id);


}
