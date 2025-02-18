package com.assignment.customer.service;

import com.assignment.customer.constants.Constants;
import com.assignment.customer.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    @Autowired
    public RestTemplate restTemplate;


    public Account createAccountForCustomer(Account account) {
        try {
            return restTemplate.postForObject(Constants.ACCOUNT_SERVICE_URL + "/saveAccount", account, Account.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create account", e);
        }
    }

    public void deleteAccount(Long accountId) {
        String deleteAccounturl=Constants.ACCOUNT_SERVICE_URL+"/deleteAccount/"+accountId;
        restTemplate.delete(deleteAccounturl);
    }
}
