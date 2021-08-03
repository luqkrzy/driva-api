package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing {@link Product}.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    Product getProduct(@PathVariable Long id) {
        return productService.find(id);
    }
    
    @PostMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    Product createProduct(@PathVariable(value = "id") final Long id, @RequestBody @Valid ProductDTO product) {
        return productService.save(id, product);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
