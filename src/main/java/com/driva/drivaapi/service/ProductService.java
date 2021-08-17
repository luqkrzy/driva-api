package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.mapper.dto.StudentProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.Student;

import java.util.List;

public interface ProductService {
   
   List<ProductDTO> findAll();
   
   Product find(Long id);
   
   ProductDTO findToProductDTO(Long id);
   
   ProductDTO save(StudentProductDTO studentProductDTO, Student student, ProductType productType);
   
   List<ProductDTO> saveAll(List<StudentProductDTO> studentProductDTOS, Student student);
   
   StudentProductDTO findToStudentProductDTO(Long id);
   
   void delete(Long id);
   
   List<ProductDTO> findAllByUserId(Long id);
   
   ProductDTO updateProduct(Long id, StudentProductDTO studentProductDTO);
}
