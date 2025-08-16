package com.kshrd.springdatajpahomework.service.implementation;

import com.kshrd.springdatajpahomework.base.PaginationInfo;
import com.kshrd.springdatajpahomework.dto.request.ProductRequest;
import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
import com.kshrd.springdatajpahomework.dto.response.ProductResponse;
import com.kshrd.springdatajpahomework.enumeration.ProductProperty;
import com.kshrd.springdatajpahomework.exception.NotFoundException;
import com.kshrd.springdatajpahomework.model.Product;
import com.kshrd.springdatajpahomework.repository.ProductRepository;
import com.kshrd.springdatajpahomework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id " + id + " not found")).toResponse();
    }

    @Override
    public PaginationResponse<ProductResponse> getAllProduct(Integer page, Integer size, ProductProperty productProperty, Sort.Direction direction) {
        PageRequest pageable = PageRequest.of(page-1, size, Sort.by(direction, productProperty.getFileName()));
        Page<Product> productPage = productRepository.findAll(pageable);

        return PaginationResponse.<ProductResponse>builder()
                .items(productPage.getContent().stream().map(Product::toResponse).toList())
                .paginationInfo(PaginationInfo.toPagination(productPage))
                .build();
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productRequest.toEntity();
        return productRepository.save(product).toResponse();
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProduct(id);
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = findProduct(id);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setUnitPrice(productRequest.getUnitPrice());
        return productRepository.save(product).toResponse();
    }
}
