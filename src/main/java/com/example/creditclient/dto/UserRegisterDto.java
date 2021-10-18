package com.example.creditclient.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class UserRegisterDto {
    private Long innerNumber;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String repeatPassword;
}
