package com.kshrd.springdatajpahomework.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderProperty {
    ID("id"), ORDER_DATE("orderDate"), TOTAL_AMOUNT("totalAmount");
    private final String fieldName;
}
