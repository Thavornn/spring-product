package com.kshrd.springdatajpahomework.dto.request;

import com.kshrd.springdatajpahomework.model.Customer;
import com.kshrd.springdatajpahomework.model.CustomerAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class CustomerRequest {

    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Customer name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(0[1-9][0-9]{7}|\\+855[1-9][0-9]{7})$",
            message = "Invalid Cambodian phone number format"
    )
    private String phoneNumber;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    public Customer toEntity() {
        return Customer.builder()
                .name(this.name)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
