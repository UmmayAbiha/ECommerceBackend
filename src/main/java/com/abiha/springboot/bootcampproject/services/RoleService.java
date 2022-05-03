package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.entities.Role;
import com.abiha.springboot.bootcampproject.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    public void save(String authority){

        Role role = new Role();
        role.setAuthority(authority);
        roleRepo.save(role);

    }
}

