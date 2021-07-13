package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
