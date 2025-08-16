package com.kshrd.springdatajpahomework.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.kshrd.springdatajpahomework.model.CustomerAccount;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Long customerId;
    private String name;

    private String address;

    private String phoneNumber;

    @JsonUnwrapped
    private CustomerAccountResponse customerAccountResponse;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
