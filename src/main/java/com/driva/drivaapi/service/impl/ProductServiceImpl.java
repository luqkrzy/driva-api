package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.ProductNotFoundException;
import com.driva.drivaapi.mapper.ProductMapper;
import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.repository.ProductRepository;
import com.driva.drivaapi.service.ProductService;
import com.driva.drivaapi.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
   
   private final ProductRepository productRepository;
   private final ProductTypeService productTypeService;
   private final ProductMapper productMapper;
   
   @Override
   public List<Product> findAll() {
	  return productRepository.findAll();
   }
   
   @Override
   public Product save(ProductDTO productDTO, Student student, ProductType productType) {
	  final Product product = productMapper.productDTOtoEntity(productDTO, student, productType);
	  return productRepository.save(product);
   }
   
   @Override
   public List<Product> saveAll(List<ProductDTO> productDTOs, Student student) {
	  return productDTOs.stream().map(
			  productDTO -> save(productDTO, student, productTypeService.find(productDTO.getProductTypeId()))
									 ).collect(Collectors.toList());
   }
   
   //   public Product save(Long studentId, ProductDTO productDTO) {
   //	  final Product mappedProduct = productMapper.productDTOtoEntity(studentId, productDTO);
   //	  return productRepository.save(mappedProduct);
   //   }
   
   @Override
   public Product find(Long id) {
	  return productRepository.findById(id).orElseThrow(
			  () -> new ProductNotFoundException("Product not found, id: " + id));
   }
   
   @Override
   public void delete(Long id) {
	  Product product = find(id);
	  productRepository.delete(product);
   }
}
