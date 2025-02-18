package com.assignment.customer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerRequest {
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerAddress;
    private Long minBalance;
    private String accountType;
    private Long initialDepositAmount;
}
