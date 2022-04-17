package com.abiha.springboot.bootcampproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CustomerDto {

    @Email
    private String email;

    private String firstName;
    private String middleName;
    private String lastName;
    public Boolean isActive;

    @Pattern(regexp = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%*!^&+=])"
            + "(?=\\S+$).{8,16}$",
            message = "Password should contain atleast 1 uppercase, 1 lowercase, 1 numeric value" +
                    " and 1 special character")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String contact;

}
