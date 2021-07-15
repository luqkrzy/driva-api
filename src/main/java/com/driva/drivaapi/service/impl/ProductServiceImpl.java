package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.mapper.ProductMapper;
import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.repository.ProductRepository;
import com.driva.drivaapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productRepository.save(productMapper.productDTOtoEntity(productDTO));
        return productMapper.entityToUserDTO(product);
    }
}
