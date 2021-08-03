package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findAllByProductTypeId_id(Long id);
    
    List<Product> findAllByStudentId_Id(Long id);
}
