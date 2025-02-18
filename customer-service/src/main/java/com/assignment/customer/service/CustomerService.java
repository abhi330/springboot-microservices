package com.assignment.customer.service;

import com.assignment.customer.controller.CustomerController;
import com.assignment.customer.dto.Account;
import com.assignment.customer.dto.CustomerRequest;
import com.assignment.customer.entity.Customer;
import com.assignment.customer.repository.CustomerRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class CustomerService {



    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountService accountService;

    public Customer saveCustomer(CustomerRequest customerRequest) {
        Account account = Account.builder()
                .Balance(customerRequest.getInitialDepositAmount())
                .minBalance(customerRequest.getMinBalance())
                .AccountType(customerRequest.getAccountType()).build();
        Account savedAccount = accountService.createAccountForCustomer(account);

        Customer customer = Customer.builder()
                .customerEmail(customerRequest.getCustomerEmail())
                .customerLastName(customerRequest.getCustomerLastName())
                .customerFirstName(customerRequest.getCustomerFirstName())
                .customerAddress(customerRequest.getCustomerAddress())
                .customerPhone(customerRequest.getCustomerPhone())
                .accountId(savedAccount.getAccountId())
                .build();
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return CollectionUtils.isEmpty(customers) ? Collections.emptyList() : customers;
    }

    public Customer getCustomerDetails(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public String deleteCustomer(Long id) {
        Customer customer=getCustomerDetails(id);

        if (Objects.nonNull(customer)) {
            customerRepository.deleteById(id);
            accountService.deleteAccount(customer.getAccountId());
            return "Customer deleted successfully";
        } else {
            return "Customer not found";
        }
    }

    public Customer updateCustomerDetails(Long id, Customer customerRequest) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setCustomerFirstName(customerRequest.getCustomerFirstName());
            existingCustomer.setCustomerLastName(customerRequest.getCustomerLastName());
            existingCustomer.setCustomerEmail(customerRequest.getCustomerEmail());
            existingCustomer.setCustomerPhone(customerRequest.getCustomerPhone());
            existingCustomer.setCustomerAddress(customerRequest.getCustomerAddress());
            return customerRepository.save(existingCustomer);
        } else {
            return null;
        }
    }

    public Customer findCustomerByIdAndEmail(Long customerId, String customerEmail) {
        return customerRepository.findByCustomerIdAndCustomerEmail(customerId, customerEmail);
    }

    public Customer findCustomerByAccountId(Long accountId) {
        Optional<Customer> customer= customerRepository.findByAccountId(accountId);
        if (customer.isPresent())
            return customer.get();
        else return  null;
    }
}
