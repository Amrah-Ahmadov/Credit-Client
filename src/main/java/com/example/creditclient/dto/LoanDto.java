package com.example.creditclient.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class LoanDto {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private BigDecimal amount;
    private String note;
    private CreditDto creditDto;
    private ReclameDto reclameDto;
    private UserDto userDto;
    private LocalDateTime creationDate;
}
