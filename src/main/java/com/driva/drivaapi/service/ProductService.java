package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.Student;

import java.util.List;

public interface ProductService {
   
   List<Product> findAll();
   
   Product save(ProductDTO productDTO, Student student, ProductType productType);
   
   List<Product> saveAll(List<ProductDTO> productDTOs, Student student);
   
   Product find(Long id);
   
   void delete(Long id);
}
