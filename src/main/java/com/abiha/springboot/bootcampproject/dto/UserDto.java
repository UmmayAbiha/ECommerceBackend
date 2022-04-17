
package com.abiha.springboot.bootcampproject.dto;

import com.abiha.springboot.bootcampproject.entities.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    @NotBlank(message = "Email is mandatory field")
    @Email
    private String email;
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    private String middleName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

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


    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}"
            + "[A-Z]{1}[1-9A-Z]{1}"
            + "Z[0-9A-Z]{1}$",
            message = "GST is not valid as per Govt. norms")
    //valid gst number : 06BZAHM6385P6Z2
    private String gst;

    private String companyName;

    private Address companyAddress;


    @Pattern(regexp="(^$|[0-9]{10})")
    private String companyContact;


}