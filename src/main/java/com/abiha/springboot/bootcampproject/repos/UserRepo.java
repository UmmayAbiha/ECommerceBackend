package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {

    User findByEmail(String email);
}
