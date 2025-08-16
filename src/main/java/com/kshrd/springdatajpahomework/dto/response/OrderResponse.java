package com.kshrd.springdatajpahomework.dto.response;

import com.kshrd.springdatajpahomework.enumeration.Status;
import com.kshrd.springdatajpahomework.model.Customer;
import com.kshrd.springdatajpahomework.model.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;

    private Status status;

    private CustomerResponse customerResponse;

    private List<ProductResponse> productResponses;
}
