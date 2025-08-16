package com.kshrd.springdatajpahomework.controller;

import com.kshrd.springdatajpahomework.base.BaseController;
import com.kshrd.springdatajpahomework.base.ApiResponse;
import com.kshrd.springdatajpahomework.dto.request.ProductRequest;
import com.kshrd.springdatajpahomework.dto.response.PaginationResponse;
import com.kshrd.springdatajpahomework.dto.response.ProductResponse;
import com.kshrd.springdatajpahomework.enumeration.ProductProperty;
import com.kshrd.springdatajpahomework.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product")
public class ProductController extends BaseController {
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get paginated list of products",
            description = "Retrieves a list of products in paginated format. You can specify the page number, page size, sorting property (from ```ProductProperty``` enum), and sort direction.")
    public ResponseEntity<ApiResponse<PaginationResponse<ProductResponse>>> getAllProduct(
            @RequestParam(defaultValue = "1") @Positive Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size,
            @RequestParam ProductProperty productProperty,
            @RequestParam Sort.Direction direction
            ) {
        PaginationResponse<ProductResponse> productResponses = productService.getAllProduct(page, size, productProperty, direction);
        return response("Product list retrieved successfully.", productResponses);
    }

    @GetMapping("/{product-id}")
    @Operation(summary = "Get product by ID", description = "Retrieves the details of a product using the specified product ID.")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable("product-id") Long id) {
        return response("Product details retrieved successfully.", productService.getProductById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product record in the system using the provided ```ProductRequest``` payload. Returns the details of the newly created product.")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return response("Product has been created successfully.", HttpStatus.CREATED, productService.createProduct(productRequest));
    }

    @DeleteMapping("/{product-id}")
    @Operation(summary = "Delete product by ID", description = "Deletes an existing product from the system using the specified product ID.")
    public ResponseEntity<ApiResponse<Object>> deleteProduct(@PathVariable("product-id") Long id) {
        productService.deleteProduct(id);
        return response("Product has been deleted successfully.");
    }

    @PutMapping("/{product-id}")
    @Operation(summary = "Update product by ID", description = "Updates the details of an existing product using the provided product ID and ```ProductRequest``` payload.")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable("product-id") Long id, @RequestBody @Valid ProductRequest productRequest) {
        return response("Product has been updated successfully.", productService.updateProduct(id, productRequest));
    }

}
