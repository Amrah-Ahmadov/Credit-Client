package com.example.creditclient.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class CreditDto {
    private Long id;
    private String name;
}
