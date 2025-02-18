package com.assignment.account.controller;

import com.assignment.account.constants.Constants;
import com.assignment.account.dto.AccountDetails;
import com.assignment.account.dto.AccountRequest;
import com.assignment.account.entity.Account;
import com.assignment.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(Constants.SAVE_ACCOUNT)
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @GetMapping(Constants.ACCOUNT_DETAILS)
    public ResponseEntity<AccountDetails> getAccountDetails(@PathVariable("id") Long accountId) {
        AccountDetails AccountDetails = accountService.getAccountDetails(accountId);
        if (AccountDetails.getAccount() != null) {
            return new ResponseEntity<>(AccountDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(Constants.ADD_MONEY)
    public ResponseEntity<String> addMoney(@RequestBody AccountRequest request) {
        boolean success = accountService.addMoney(request.getAccountId(), request.getCustomerId(), request.getCustomerEmail(), request.getAmount());
        if (success) {
            return new ResponseEntity<>("Money added successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add money", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(Constants.WITHDRAW_MONEY)
    public ResponseEntity<String> withdrawMoney(@RequestBody AccountRequest request) {
        boolean success = accountService.withdrawMoney(request.getAccountId(), request.getCustomerId(), request.getCustomerEmail(), request.getAmount());
        if (success) {
            return new ResponseEntity<>("Money withdrawn successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to withdraw money", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(Constants.DELETE_ACCOUNT)
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id){
        try{
        String response = accountService.deleteAccount(id);
        if (response.equals("Account got deleted successfully")){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            log.error("Error deleting customer", e);
            return new ResponseEntity<>("Error deleting customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}