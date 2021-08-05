package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.ProductNotFoundException;
import com.driva.drivaapi.mapper.ProductMapper;
import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.mapper.dto.StudentProductDTO;
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
   public Product save(StudentProductDTO studentProductDTO, Student student, ProductType productType) {
	  final Product product = productMapper.studentProductDTOtoEntity(studentProductDTO, student, productType);
	  return productRepository.save(product);
   }
   
   @Override
   public List<Product> saveAll(List<StudentProductDTO> studentProductDTOS, Student student) {
	  return studentProductDTOS.stream().map(
									   productDTO ->
											   save(productDTO, student, productTypeService.find(productDTO.getProductTypeId())))
							   .collect(Collectors.toList());
   }
   
   @Override
   public StudentProductDTO findToStudentProductDTO(Long id) {
	  final Product product = find(id);
	  return productMapper.entityToStudentProductDTO(product);
   }
   
   @Override
   public Product find(Long id) {
	  return productRepository.findById(id).orElseThrow(
			  () -> new ProductNotFoundException("Product not found, id: " + id));
   }
   
   @Override
   public ProductDTO findToProductDTO(Long id) {
	  Product product = find(id);
	  return productMapper.entityToProductDTO(product);
   }
   
   @Override
   public void delete(Long id) {
	  Product product = find(id);
	  productRepository.delete(product);
   }
}
