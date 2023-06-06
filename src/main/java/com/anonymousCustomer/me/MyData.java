package com.anonymousCustomer.me;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MyData {
    @NotBlank
//    @Pattern()   learn how to use it
    @Email
    public String email;
    @NotBlank
    public String password;
    public String lastName;
    public String firstName;

    private String token;
}
