package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
