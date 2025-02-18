package com.assignment.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    private Long AccountId;
    private Long Balance;
    private String AccountType;
    private Long minBalance;
}
