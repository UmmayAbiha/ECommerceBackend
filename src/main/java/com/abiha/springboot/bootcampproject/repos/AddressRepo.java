package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
