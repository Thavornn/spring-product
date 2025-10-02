// package com.kshrd.springdatajpahomework.controller;

// import com.kshrd.springdatajpahomework.base.ApiResponse;
// import com.kshrd.springdatajpahomework.base.BaseController;
// import com.kshrd.springdatajpahomework.dto.request.CustomerRequest;
// import com.kshrd.springdatajpahomework.dto.response.CustomerResponse;
// import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
// import com.kshrd.springdatajpahomework.enumeration.CustomerProperty;
// import com.kshrd.springdatajpahomework.service.CustomerService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
// import jakarta.validation.constraints.Positive;
// import lombok.RequiredArgsConstructor;
// import org.springframework.data.domain.Sort;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/customers")
// @RequiredArgsConstructor
// @Tag(name = "Customer")
// public class CustomerController extends BaseController {
//     private final CustomerService customerService;

//     @GetMapping
//     @Operation(summary = "Get paginated list of customers", description = "Retrieves all customers in a paginated format. You can specify the page number, page size, sorting property, and sort direction.")
//     public ResponseEntity<ApiResponse<PaginationResponse<CustomerResponse>>> getAllCustomer(
//             @RequestParam(defaultValue = "1") @Positive Integer page,
//             @RequestParam(defaultValue = "10") @Positive Integer size,
//             @RequestParam CustomerProperty customerProperty,
//             @RequestParam Sort.Direction direction
//     ) {
//         PaginationResponse<CustomerResponse> paginationResponse = customerService.getAllCustomer(page, size, customerProperty, direction);
//         return response("", paginationResponse);
//     }

//     @GetMapping("/{customer-id}")
//     @Operation(summary = "Get customer by ID", description =  "Fetches detailed information about a single customer using the provided customer ID.")
//     public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable("customer-id") Long id) {
//         return response("Customer details retrieved successfully.", customerService.getCustomerById(id));
//     }

//     @PostMapping
//     @Operation(summary = "Create a new customer", description = "Accepts a valid ```CustomerRequest``` object to create a new customer record. Returns the newly created customer's details.")
//     public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
//         return response("Customer has been created successfully.", HttpStatus.CREATED, customerService.createCustomer(customerRequest));
//     }

//     @PutMapping("/{customer-id}")
//     @Operation(summary = "Update existing customer", description = "Updates the details of an existing customer based on the provided customer ID and request body.")
//     public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(@PathVariable("customer-id") Long id, @RequestBody @Valid CustomerRequest customerRequest) {
//         return response("Customer has been updated successfully.", customerService.updateCustomer(id, customerRequest));
//     }

//     @DeleteMapping("/{customer-id}")
//     @Operation(summary = "Delete customer", description = "Removes a customer record from the system based on the provided customer ID.")
//     public ResponseEntity<ApiResponse<Object>> deleteCustomer(@PathVariable("customer-id") Long id) {
//         customerService.deleteCustomer(id);
//         return response("Customer has been deleted successfully.");
//     }
// }
