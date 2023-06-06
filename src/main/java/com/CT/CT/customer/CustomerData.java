package com.CT.CT.customer;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
//@AllArgsConstructor
//@NoArgsConstructor @Builder
public class CustomerData{
    public  String Id;
    public String  firstName;
    public String lastName;
    public String email;
    public String Password;

    public Long version;
    public String EmailPref;
    public  String value;
    public String tokenValue;
    public String currentPassword;
    public String newPassword;
    public String middleName;
    public String companyName;
    public String customerNumber;
    public String externalId;
}
