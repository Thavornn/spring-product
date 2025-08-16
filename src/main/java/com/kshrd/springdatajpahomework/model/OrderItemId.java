package com.kshrd.springdatajpahomework.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Builder
public class OrderItemId {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "order_id")
    private Long orderId;


}
