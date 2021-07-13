package com.driva.drivaapi.controller;


import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


}
