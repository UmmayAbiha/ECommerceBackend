package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long>{
    Role findByAuthority(String name);
}
