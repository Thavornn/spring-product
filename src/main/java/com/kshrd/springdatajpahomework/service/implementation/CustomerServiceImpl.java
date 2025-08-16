package com.kshrd.springdatajpahomework.service.implementation;

import com.kshrd.springdatajpahomework.base.PaginationInfo;
import com.kshrd.springdatajpahomework.dto.request.CustomerRequest;
import com.kshrd.springdatajpahomework.dto.response.CustomerResponse;
import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
import com.kshrd.springdatajpahomework.enumeration.CustomerProperty;
import com.kshrd.springdatajpahomework.exception.BadRequestException;
import com.kshrd.springdatajpahomework.exception.NotFoundException;
import com.kshrd.springdatajpahomework.model.Customer;
import com.kshrd.springdatajpahomework.model.CustomerAccount;
import com.kshrd.springdatajpahomework.repository.CustomerRepository;
import com.kshrd.springdatajpahomework.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public PaginationResponse<CustomerResponse> getAllCustomer(Integer page, Integer size, CustomerProperty customerProperty, Sort.Direction direction) {
        PageRequest pageable = PageRequest.of(page-1, size, Sort.by(direction, customerProperty.getFieldName()));
        Page<Customer> customer = customerRepository.findAll(pageable);
        return PaginationResponse.<CustomerResponse>builder()
                .items(customer.getContent().stream().map(Customer::toResponse).toList())
                .paginationInfo(PaginationInfo.toPagination(customer))
                .build();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id "+ id + " not found")).toResponse();
    }

    public Customer findCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id "+ id + " not found"));
    }

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer c = customerRepository.findByCustomerAccountUsername(customerRequest.getUsername());
        if (c != null) {
            throw new BadRequestException("Username is already taken");
        }
        Customer customer = customerRequest.toEntity();
        CustomerAccount customerAccount = CustomerAccount.builder()
                .username(customerRequest.getUsername())
                .password(customerRequest.getPassword())
                .build();

        customerAccount.setCustomer(customer);
        customer.setCustomerAccount(customerAccount);
        return customerRepository.save(customer).toResponse();
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer c = customerRepository.findByCustomerAccountUsername(customerRequest.getUsername());
        if (c != null) {
            throw new BadRequestException("Username is already taken");
        }
        Customer customer = findCustomer(id);
        customer.setName(customerRequest.getName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());

        customer.getCustomerAccount().setUsername(customerRequest.getUsername());
        customer.getCustomerAccount().setPassword(customerRequest.getPassword());

        return customerRepository.save(customer).toResponse();
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = findCustomer(id);
        customerRepository.delete(customer);
    }
}
