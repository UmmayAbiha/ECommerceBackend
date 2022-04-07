package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
