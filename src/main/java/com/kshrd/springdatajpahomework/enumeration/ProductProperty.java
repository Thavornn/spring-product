package com.kshrd.springdatajpahomework.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductProperty {
    ID("id"),
    NAME("name"),
    UNIT_PRICE("unitPrice");

    private final String fileName;
}
