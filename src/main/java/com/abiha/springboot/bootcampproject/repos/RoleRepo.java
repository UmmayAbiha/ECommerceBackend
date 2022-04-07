package com.abiha.springboot.bootcampproject.repos;

import com.abiha.springboot.bootcampproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long>{

    Role findByName(String name);
}
