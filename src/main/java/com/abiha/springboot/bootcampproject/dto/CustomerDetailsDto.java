package com.abiha.springboot.bootcampproject.dto;

import lombok.Data;

@Data
public class CustomerDetailsDto {

    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    public Boolean isActive;
    private String contact;

}
