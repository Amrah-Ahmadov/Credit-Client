package com.example.creditclient.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class UserDto {
    private Long id;
    private Long innerNumber;
    private String name;
    private String surname;
    private String username;
}
