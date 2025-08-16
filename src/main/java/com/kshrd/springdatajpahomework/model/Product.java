package com.kshrd.springdatajpahomework.model;

import com.kshrd.springdatajpahomework.dto.response.ProductResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(name = "unit_price", nullable = false, precision = 8, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private String description;

    public ProductResponse toResponse() {
        return ProductResponse.builder()
                .productId(this.getId())
                .name(this.name)
                .unitPrice(this.unitPrice)
                .description(this.description)
                .build();
    }
}
