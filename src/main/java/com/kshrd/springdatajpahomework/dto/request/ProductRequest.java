package com.kshrd.springdatajpahomework.dto.request;

import com.kshrd.springdatajpahomework.model.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Unit price must be greater than 0")
    private BigDecimal unitPrice;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    public Product toEntity() {
        return Product.builder()
                .id(null)
                .name(this.name)
                .unitPrice(this.unitPrice)
                .description(this.description)
                .build();
    }
}
