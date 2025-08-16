package com.kshrd.springdatajpahomework.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.kshrd.springdatajpahomework.dto.response.CustomerResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Customer extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonUnwrapped
    private CustomerAccount customerAccount;

    public CustomerResponse toResponse() {
        return CustomerResponse.builder()
                .customerId(this.getId())
                .address(this.address)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .customerAccountResponse(this.customerAccount.toResponse())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }

}
