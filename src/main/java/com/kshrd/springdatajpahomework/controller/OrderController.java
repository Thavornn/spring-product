// package com.kshrd.springdatajpahomework.controller;

// import com.kshrd.springdatajpahomework.base.ApiResponse;
// import com.kshrd.springdatajpahomework.base.BaseController;
// import com.kshrd.springdatajpahomework.dto.request.OrderRequest;
// import com.kshrd.springdatajpahomework.dto.response.OrderResponse;
// import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
// import com.kshrd.springdatajpahomework.enumeration.OrderProperty;
// import com.kshrd.springdatajpahomework.enumeration.Status;
// import com.kshrd.springdatajpahomework.service.OrderService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
// import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.Positive;
// import lombok.RequiredArgsConstructor;
// import org.springframework.data.domain.Sort;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/orders")
// @RequiredArgsConstructor
// @Tag(name = "Order")
// public class OrderController extends BaseController {
//     private final OrderService orderService;

//     @GetMapping("/customers/{customer-id}")
//     @Operation(summary = "List orders by customer (paginated)", description = "Retrieves a paginated list of orders for the specified customer. Supports page/size and sorting by ```OrderProperty``` with ```Sort.Direction```.")
//     public ResponseEntity<ApiResponse<PaginationResponse<OrderResponse>>> getAllOrderByCustomer(
//             @PathVariable("customer-id") Long id,
//             @RequestParam(defaultValue = "1") @Positive Integer page,
//             @RequestParam(defaultValue = "10") @Positive Integer size,
//             @RequestParam OrderProperty orderProperty,
//             @RequestParam Sort.Direction direction
//                                                                                                 ) {
//         return response("Order list by customer retrieved successfully.", orderService.getAllOrderByCustomer(id, page, size, orderProperty, direction));
//     }

//     @GetMapping("{order-id}")
//     @Operation(summary = "Get order details by ID", description = "Fetches a single order, including its line items and status, using the provided order ID.")
//     public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable("order-id") Long id) {
//         return response("Order details retrieved successfully.", orderService.getOrderById(id));
//     }

//     @PostMapping("/customers/{customer-id}")
//     @Operation(summary = "Create order(s) for a customer", description =  "Creates one new order for the given customer using a non-empty list of ```OrderRequest``` items (each item represents an order line). Returns the created order with calculated totals.")
//     public ResponseEntity<ApiResponse<OrderResponse>> createOrderByCustomer(@PathVariable("customer-id") Long id, @RequestBody @Valid @NotEmpty List<OrderRequest> orderRequests) {
//         return response("Order has been created successfully.", HttpStatus.CREATED, orderService.createOrderByCustomer(id, orderRequests));
//     }

//     @PutMapping("/{order-id}/status")
//     @Operation(summary = "Update order status", description = "Updates the status of an existing order using the provided order ID and OrderStatus value (e.g., ```PENDING```, ```PAID```, ```SHIPPED```, ```CANCELLED```).")
//     public ResponseEntity<ApiResponse<OrderResponse>> updateOrderStatus(@PathVariable("order-id") Long id, @RequestParam Status status) {
//         return response("Order status has been updated successfully.", orderService.updateOrderStatus(id, status));
//     }

//     @DeleteMapping("/{order-id}")
//     @Operation(summary = "Delete order by ID", description = "Deletes an order from the system using the specified order ID.")
//     public ResponseEntity<ApiResponse<Object>> deleteOrderById(@PathVariable("order-id") Long id) {
//         orderService.deleteOrderById(id);
//         return response("Order has been deleted successfully.");
//     }

// }
