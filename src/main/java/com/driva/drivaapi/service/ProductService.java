package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.model.product.Product;

import java.util.List;

public interface ProductService {
   
   List<Product> findAll();
   
   Product save(Long studentId, ProductDTO product);
   
   Product find(Long id);
   
   void delete(Long id);
}
