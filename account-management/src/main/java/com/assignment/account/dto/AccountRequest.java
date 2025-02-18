package com.assignment.account.dto;

import lombok.Data;

@Data
public class AccountRequest {
    private Long customerId;
    private String customerEmail;
    private Long accountId;
    private Long amount;
}
