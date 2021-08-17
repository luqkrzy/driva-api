package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.mapper.dto.StudentProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.service.ProductService;
import com.driva.drivaapi.service.ProductTypeService;
import com.driva.drivaapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing {@link Product}.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;
    private final StudentService studentService;
    private final ProductTypeService productTypeService;
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }
    
    @GetMapping("student/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    List<ProductDTO> getProductsByUserId(@PathVariable Long id) {
        return productService.findAllByUserId(id);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    ProductDTO getProduct(@PathVariable Long id) {
        return productService.findToProductDTO(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    ProductDTO createProduct(@RequestBody @Valid StudentProductDTO studentProductDTO) {
        final Student student = studentService.find(studentProductDTO.getStudentId());
        final ProductType productType = productTypeService.find(studentProductDTO.getProductTypeId());
        return productService.save(studentProductDTO, student, productType);
    }
    
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid StudentProductDTO studentProductDTO) {
        return productService.updateProduct(id, studentProductDTO);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
