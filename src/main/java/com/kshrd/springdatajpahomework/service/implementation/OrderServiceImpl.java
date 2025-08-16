package com.kshrd.springdatajpahomework.service.implementation;

import com.kshrd.springdatajpahomework.base.PaginationInfo;
import com.kshrd.springdatajpahomework.dto.request.OrderRequest;
import com.kshrd.springdatajpahomework.dto.response.CustomerResponse;
import com.kshrd.springdatajpahomework.dto.response.OrderResponse;
import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
import com.kshrd.springdatajpahomework.enumeration.OrderProperty;
import com.kshrd.springdatajpahomework.enumeration.Status;
import com.kshrd.springdatajpahomework.exception.NotFoundException;
import com.kshrd.springdatajpahomework.model.*;
import com.kshrd.springdatajpahomework.repository.CustomerRepository;
import com.kshrd.springdatajpahomework.repository.OrderRepository;
import com.kshrd.springdatajpahomework.repository.ProductRepository;
import com.kshrd.springdatajpahomework.service.OrderService;
import com.kshrd.springdatajpahomework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order with id "+ id+ " not found"));
    }
    @Override
    public PaginationResponse<OrderResponse> getAllOrderByCustomer(Long id, Integer page, Integer size, OrderProperty orderProperty, Sort.Direction direction) {
        PageRequest pageable = PageRequest.of(page-1, size, Sort.by(direction, orderProperty.getFieldName()));
        Page<Order> orders = orderRepository.findOrderByCustomerId(id, pageable);
        return PaginationResponse.<OrderResponse>builder()
                .items(orders.getContent().stream().map(Order::toResponse).toList())
                .paginationInfo(PaginationInfo.toPagination(orders))
                .build();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order with id "+ id+ " not found")).toResponse();
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        Order order = findOrder(id);
        orderRepository.delete(order);
    }

    @Override
    @Transactional
    public OrderResponse createOrderByCustomer(Long id, List<OrderRequest> orderRequests) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id "+ id + " not found"));
        Order order = Order.builder()
                .customer(customer)
                .status(Status.PENDING)
                .build();
        List<OrderItem> orderItems = orderRequests.stream().map(req -> {
            Product product = productRepository.findById(req.getProductId()).orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));
            OrderItemId itemId = OrderItemId.builder().orderId(order.getId()).productId(product.getId()).build();
            return OrderItem.builder()
                    .id(itemId)
                    .product(product)
                    .quantity(req.getQuantity())
                    .build();
        }).toList();

        order.setOrderItems(orderItems);
        order.setTotalAmount(BigDecimal.valueOf(orderItems.size()));
        orderItems.forEach(item -> item.setOrder(order));

        return orderRepository.save(order).toResponse();
    }

    @Override
    @Transactional
    public OrderResponse updateOrderStatus(Long id, Status status) {
        Order order = findOrder(id);
        order.setStatus(status);
        return orderRepository.save(order).toResponse();
    }


}
