package com.driva.drivaapi.service;

import com.driva.drivaapi.model.product.ProductType;

import java.util.List;

public interface ProductTypeService {
   
   List<ProductType> findAll();
   
   ProductType find(Long id);
   
   ProductType save(ProductType productType);
   
   void delete(Long id);
   
   ProductType update(Long id, ProductType productType);
}
