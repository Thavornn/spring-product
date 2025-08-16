package com.kshrd.springdatajpahomework.service;

import com.kshrd.springdatajpahomework.dto.request.CustomerRequest;
import com.kshrd.springdatajpahomework.dto.response.CustomerResponse;
import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
import com.kshrd.springdatajpahomework.enumeration.CustomerProperty;
import org.springframework.data.domain.Sort;

public interface CustomerService {
    PaginationResponse<CustomerResponse> getAllCustomer(Integer page, Integer size, CustomerProperty customerProperty, Sort.Direction direction);

    CustomerResponse getCustomerById(Long id);

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);

    void deleteCustomer(Long id);
}
