package com.abiha.springboot.bootcampproject.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;

    private String city;
    private String state;
    private String Country;
    private String addressLine;
    private String zipCode;
    private String label;
}
