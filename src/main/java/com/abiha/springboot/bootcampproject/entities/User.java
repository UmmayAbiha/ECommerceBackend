package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;
    private Boolean isDeleted = false;
    public Boolean isActive= false;
    private Boolean isExpired = false;
    private Boolean isLocked = false;
    private Integer invalidAttemptCount;
    private Date passwordUpdateDate;


    public User(String firstName, String middleName, String lastName, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Address> addresses;


    @JsonIgnore
    @OneToOne(mappedBy = "user",cascade= CascadeType.ALL)
    private Customer customer;

    @JsonIgnore
    @OneToOne(mappedBy = "user",cascade= CascadeType.ALL)
    private Seller seller;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<com.abiha.springboot.bootcampproject.entities.Role> roles;

    @Transient
    public String resetPasswordToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public void addRole(Role role){
        if(role!=null){
            if(this.roles==null){
                roles=new HashSet<>();
            }
            this.roles.add(role);
        }
    }

    public boolean hasRole(Role role){
        for(Role rol:roles){
            if(rol.getAuthority().equals(role.getAuthority())){
                return true;
            }
        }
        return false;
    }

    public void addAddress(Address address) {
        if (address != null) {
            if (addresses == null) {
                addresses = new HashSet<>();
            }
            address.setUser(this);
            addresses.add(address);
        }
    }
}
