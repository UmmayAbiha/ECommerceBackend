package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);

    List<User> findAll();


}
