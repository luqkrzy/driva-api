package com.driva.drivaapi.mapper;

import com.driva.drivaapi.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductTypeMapper {
   
   private final ProductTypeRepository productTypeRepository;
}
