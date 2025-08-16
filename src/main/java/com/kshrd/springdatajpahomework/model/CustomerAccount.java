package com.kshrd.springdatajpahomework.model;

import com.kshrd.springdatajpahomework.dto.response.CustomerAccountResponse;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "customer_accounts")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerAccount {
    @Id
    @Column(name = "customer_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @PrePersist
    private void prePersist() {
        if (isActive == null) {
            isActive = true;
        }
    }

    public CustomerAccountResponse toResponse() {
        return CustomerAccountResponse.builder()
                .username(this.username)
                .password(this.password)
                .isActive(this.isActive)
                .build();
    }

}
