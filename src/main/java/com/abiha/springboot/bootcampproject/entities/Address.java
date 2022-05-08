package com.abiha.springboot.bootcampproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String state;
    private String Country;
    private String addressLine;
    private String zipCode;
    private String label;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Address(String city, String state, String country, String addressLine, String zipCode, String label, User user) {
        this.city = city;
        this.state = state;
        Country = country;
        this.addressLine = addressLine;
        this.zipCode = zipCode;
        this.label = label;
        this.user = user;
    }
}
