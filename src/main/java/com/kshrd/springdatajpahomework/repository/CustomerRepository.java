package com.kshrd.springdatajpahomework.repository;

import com.kshrd.springdatajpahomework.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerAccountUsername(String username);
}
