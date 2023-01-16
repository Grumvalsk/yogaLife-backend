package com.grumvalski.yogaLifebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRegistration {
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
}
