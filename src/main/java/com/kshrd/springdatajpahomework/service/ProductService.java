package com.kshrd.springdatajpahomework.service;

import com.kshrd.springdatajpahomework.dto.request.ProductRequest;
import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
import com.kshrd.springdatajpahomework.dto.response.ProductResponse;
import com.kshrd.springdatajpahomework.enumeration.ProductProperty;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    PaginationResponse<ProductResponse> getAllProduct(Integer page, Integer size, ProductProperty productProperty, Sort.Direction direction);

    ProductResponse getProductById(Long id);

    ProductResponse createProduct(ProductRequest productRequest);

    void deleteProduct(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);
}
