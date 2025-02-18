package com.assignment.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long customerId;
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerAddress;
    private Long accountId;
}
