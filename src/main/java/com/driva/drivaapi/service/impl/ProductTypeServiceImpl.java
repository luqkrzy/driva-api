package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.ProductTypeNotFoundException;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.repository.ProductTypeRepository;
import com.driva.drivaapi.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
   
   private final ProductTypeRepository productTypeRepository;
   
   @Override
   public List<ProductType> findAll() {
	  return productTypeRepository.findAll();
   }
   
   @Override
   public ProductType find(Long id) {
	  return productTypeRepository.findById(id).orElseThrow(
			  () -> new ProductTypeNotFoundException("Product Type not found, id: " + id));
   }
   
   @Override
   public ProductType save(ProductType productType) {
	  return productTypeRepository.save(productType);
   }
   
   @Override
   public void delete(Long id) {
	  ProductType productType = find(id);
	  productTypeRepository.delete(productType);
   }
   
   @Override
   public ProductType update(Long id, ProductType productType) {
	  final ProductType type = find(id);
	  ProductType updatedProductType = type.update(productType);
	  return productTypeRepository.save(updatedProductType);
   }
}
