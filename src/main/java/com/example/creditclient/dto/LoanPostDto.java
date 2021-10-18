package com.example.creditclient.dto;

import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class LoanPostDto {
    private String name;
    private String surname;
    private String address;
    private BigDecimal amount;
    private String note;
    private Long credit;
    private Long reclame;
}
