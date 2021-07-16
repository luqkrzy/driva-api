package com.driva.drivaapi.mapper;

import com.driva.drivaapi.exception.ProductTypeNotFoundException;
import com.driva.drivaapi.exception.StudentNotFoundException;
import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.repository.ProductTypeRepository;
import com.driva.drivaapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final StudentRepository studentRepository;
    private final ProductTypeRepository productTypeRepository;


    public ProductDTO entityToUserDTO(Product product) {
        return new ProductDTO(product);
    }


    public List<ProductDTO> entitiesToProductDTOs(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream().filter(Objects::nonNull).map(this::entityToUserDTO).collect(Collectors.toList());
    }

    public List<Product> productDTOsToEntities(List<ProductDTO> productDTOs) {
        if (productDTOs == null) {
            return null;
        }
        return productDTOs.stream().map(this::productDTOtoEntity).collect(Collectors.toList());
    }


    public Product productDTOtoEntity(ProductDTO productDTO) {

        ProductType productType = productTypeRepository.findById(productDTO.getProductTypeId())
                .orElseThrow(() -> new ProductTypeNotFoundException(String.format("Product type with id: %d does not exist", productDTO.getProductTypeId())));
        Student student = studentRepository.findById(productDTO.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(String.format("Student with id: %d does not exist", productDTO.getStudentId())));

        return Product.builder()
                .productTypeId(productType)
                .studentId(student)
                .hoursLeft(productDTO.getHoursLeft())
                .bookOnline(productDTO.getBookOnline())
                .isPaid(productDTO.getIsPaid())
                .price(productDTO.getPrice())
                .build();
    }

}
