package com.kshrd.springdatajpahomework.service;

import com.kshrd.springdatajpahomework.dto.request.OrderRequest;
import com.kshrd.springdatajpahomework.dto.response.OrderResponse;
import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
import com.kshrd.springdatajpahomework.enumeration.OrderProperty;
import com.kshrd.springdatajpahomework.enumeration.Status;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    PaginationResponse<OrderResponse> getAllOrderByCustomer(Long id, Integer page, Integer size, OrderProperty orderProperty, Sort.Direction direction);

    OrderResponse getOrderById(Long id);

    void deleteOrderById(Long id);

    OrderResponse createOrderByCustomer(Long id, List<OrderRequest> orderRequests);

    OrderResponse updateOrderStatus(Long id, Status status);
}
