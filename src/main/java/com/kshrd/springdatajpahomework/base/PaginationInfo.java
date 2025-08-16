package com.kshrd.springdatajpahomework.base;

import lombok.*;
import org.springframework.data.domain.Page;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationInfo {
    private Long totalElements;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;

    public static PaginationInfo toPagination(Page<?> items) {
        return PaginationInfo.builder()
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber()+1)
                .pageSize(items.getSize())
                .totalPages(items.getTotalPages())
                .build();
    }

}
