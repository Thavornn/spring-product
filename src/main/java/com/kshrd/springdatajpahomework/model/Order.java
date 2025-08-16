package com.kshrd.springdatajpahomework.model;

import com.kshrd.springdatajpahomework.dto.response.OrderResponse;
import com.kshrd.springdatajpahomework.dto.response.ProductResponse;
import com.kshrd.springdatajpahomework.enumeration.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Order extends BaseEntity {
    @CreationTimestamp
    @Column(updatable = false, name = "order_date")
    private LocalDateTime orderDate;
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public OrderResponse toResponse() {
        List<ProductResponse> productResponses = this.orderItems != null
                ? this.orderItems.stream()
                .map(OrderItem::toResponse)
                .toList()
                : List.of();

        return OrderResponse.builder()
                .orderId(this.getId())
                .orderDate(this.orderDate)
                .totalAmount(this.totalAmount)
                .status(this.status)
                .customerResponse(this.customer.toResponse())
                .productResponses(productResponses)
                .build();
    }
}
