package com.practice.client.modal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModal {
    private String firstName;
    private String lastName;
    private String email;

    // add logic to validate password and matchingPassword before saving data
    private String password;
    private String matchingPassword;
}
