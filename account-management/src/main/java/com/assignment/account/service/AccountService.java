package com.assignment.account.service;


import com.assignment.account.dto.AccountDetails;
import com.assignment.account.dto.Customer;
import com.assignment.account.entity.Account;
import com.assignment.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerValidationService customerValidationService;

    public AccountDetails getAccountDetails(Long accountId) {
        Account account= accountRepository.findById(accountId).orElse(null);
        Customer customer = customerValidationService.getCustomerByAccountId(accountId);
        AccountDetails accountDetails=AccountDetails.builder().account(account).customer(customer).build();
        return accountDetails;
    }

    public boolean addMoney(Long accountId, Long customerId, String customerEmail, Long amount) {
        boolean validCustomer = customerValidationService.validateCustomerDetails(customerId, customerEmail, accountId);
        if (validCustomer) {
            Account account = accountRepository.findById(accountId).orElse(null);
            if (account != null && amount > 0) {
                account.setBalance(account.getBalance() + amount);
                accountRepository.save(account);
                return true;
            }

        }
        return false;
    }
    public boolean withdrawMoney(Long accountId, Long customerId, String customerEmail, Long amount) {
        boolean validCustomer = customerValidationService.validateCustomerDetails(customerId, customerEmail, accountId);
        if (validCustomer) {
            Account account = accountRepository.findById(accountId).orElse(null);
            if (account != null && amount > 0 && account.getBalance()-amount >= account.getMinBalance()) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
                return true;
            }
        }
        return false;
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public String  deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return "Account got deleted successfully";
        }
        return " Account not found";
    }
}
