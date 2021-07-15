package com.driva.drivaapi.service;

import com.driva.drivaapi.model.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product save(Product product);
}
