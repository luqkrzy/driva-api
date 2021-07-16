package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductTypeId_id(Long id);

    List<Product> findAllByStudentId_Id(Long id);
}
