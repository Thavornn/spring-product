package com.kshrd.springdatajpahomework.model;

import com.kshrd.springdatajpahomework.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    public ProductResponse toResponse() {
        return ProductResponse.builder()
                .productId(this.product.getId())
                .name(this.product.getName())
                .unitPrice(this.product.getUnitPrice())
                .description(this.product.getDescription())
                .build();
    }

}
