package com.kshrd.springdatajpahomework.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CustomerProperty {
    ID("id"), NAME("name");

    private final String fieldName;

}
