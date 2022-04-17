package com.abiha.springboot.bootcampproject.dto;

import com.abiha.springboot.bootcampproject.entities.Address;
import lombok.Data;

import java.util.Set;

@Data
public class SellerDetailsDto {

    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    public Boolean isActive;
    private String gst;
    private String companyName;
    private String companyContact;
    private Set<Address> companyAddress;
}
