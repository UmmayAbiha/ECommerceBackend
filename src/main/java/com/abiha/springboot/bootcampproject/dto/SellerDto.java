package com.abiha.springboot.bootcampproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SellerDto {

    @NotBlank(message = "Email is mandatory field")
    @Email
    private String email;
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Password is mandatory")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String confirmPassword;

    String companyContact;

    @NotBlank(message = "GST field is mandatory")
    String gst;
    //valid gst number : 06BZAHM6385P6Z2

    @NotBlank(message = "Company name is mandatory")
    String companyName;

    private boolean isDeleted;
    private boolean isActive;
    private boolean isExpired;
    private boolean isLocked;
    private int invalidAttempCount;
    private String passwordUpdateDate;


    public SellerDto(){
        this.setDeleted(Boolean.FALSE);
        this.setExpired(false);
        this.setLocked(true);
        this.setInvalidAttempCount(0);
        this.setPasswordUpdateDate("today");
        this.setActive(false);
    }

}