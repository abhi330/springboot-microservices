package com.assignment.account.service;

import com.assignment.account.constants.Constants;
import com.assignment.account.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CustomerValidationService {
    @Autowired
    private RestTemplate restTemplate;

    public boolean validateCustomerDetails(Long customerId, String customerEmail, Long accountId) {
        String getCustomerUrl=Constants.CUSTOMER_SERVICE_URL+"/findByIdAndEmail?customerId="+customerId+"&customerEmail="+customerEmail;
        Customer customer = restTemplate.getForObject(getCustomerUrl,Customer.class);
        return Objects.nonNull(customer)&&customer.getAccountId().equals(accountId);
    }

    public Customer getCustomerByAccountId(Long accountId) {
        String findCustomeByAccountIdUrl =Constants.CUSTOMER_SERVICE_URL+"/findByAccountId/"+accountId;
        Customer customer = restTemplate.getForObject(findCustomeByAccountIdUrl,Customer.class);
        return customer;

    }
}
