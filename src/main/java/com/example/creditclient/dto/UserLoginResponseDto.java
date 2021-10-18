package com.example.creditclient.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class UserLoginResponseDto {
    private String username;
    String jwt;
}
