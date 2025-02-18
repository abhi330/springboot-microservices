package com.assignment.customer.controller;


import com.assignment.customer.constants.Constants;
import com.assignment.customer.dto.CustomerRequest;
import com.assignment.customer.entity.Customer;
import com.assignment.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.CUSTOMER_URL)
@Slf4j
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @PostMapping(Constants.SAVE_CUSTOMER)
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        try {
            Customer savedCustomer = customerService.saveCustomer(customerRequest);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error saving customer", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping(Constants.FETCH_CUSTOMER_BY_ID)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerDetails(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(Constants.ALL_CUSTOMERS)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping(Constants.UPDATE_CUSTOMER)
    public ResponseEntity<Customer> updateCustomerDetails(@PathVariable("id") Long id, @RequestBody Customer customerRequest) {
        try {
            Customer updatedCustomer = customerService.updateCustomerDetails(id, customerRequest);
            if (updatedCustomer != null) {
                return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error updating customer details", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(Constants.DELETE_CUSTOMER)
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
        try {
            String response = customerService.deleteCustomer(id);
            if (response.equals("Customer deleted successfully")) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error deleting customer", e);
            return new ResponseEntity<>("Error deleting customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(Constants.FIND_BY_ID_EMAIL)
    public Customer findCustomer(@RequestParam Long customerId, @RequestParam String customerEmail) {
        Customer customer = customerService.findCustomerByIdAndEmail(customerId, customerEmail);
        if (customer != null) {
            return customer;
        } else {
            return null;
        }
    }

    @GetMapping(Constants.FIND_BY_ACCOUNT_ID)
    public Customer getCustomerByAccountId(@PathVariable("accountId") Long accountId){
        log.info("fetching customer by account id");
        return customerService.findCustomerByAccountId(accountId);
    }

}
