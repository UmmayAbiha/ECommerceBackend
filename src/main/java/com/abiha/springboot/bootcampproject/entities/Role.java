package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;


    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   @Override
    public String getAuthority() {
       return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", user=" + user +
                '}';
    }
}
