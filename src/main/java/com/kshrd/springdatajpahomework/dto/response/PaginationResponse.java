package com.kshrd.springdatajpahomework.dto.response;

import com.kshrd.springdatajpahomework.base.PaginationInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class PaginationResponse<T>{
    private List<T> items;
    private PaginationInfo paginationInfo;
}
