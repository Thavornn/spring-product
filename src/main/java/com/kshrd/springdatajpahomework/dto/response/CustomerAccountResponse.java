package com.kshrd.springdatajpahomework.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAccountResponse {
    private String username;
    private String password;
    private Boolean isActive;
}
