package com.assignment.customer.repository;

import com.assignment.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCustomerIdAndCustomerEmail(Long customerId, String customerEmail);
    Optional<Customer> findByAccountId(Long accountId);
}
