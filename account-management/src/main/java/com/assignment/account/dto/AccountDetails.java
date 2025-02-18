package com.assignment.account.dto;

import com.assignment.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetails {
    private Account account;
    private Customer customer;
}
