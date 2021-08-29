package com.driva.drivaapi.controller;

import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.service.ProductTypeService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-type")
public class ProductTypeController {
   
   private final ProductTypeService productTypeService;
   
   @GetMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   List<ProductType> getAllProductTypes() {
      return productTypeService.findAll();
   }
   
   @GetMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   ProductType findProduct(@PathVariable Long id) {
      return productTypeService.find(id);
   }
   
   @PostMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.CREATED)
   ProductType createProductType(@RequestBody @Valid ProductType product) {
      return productTypeService.save(product);
   }
   
   @PatchMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.OK)
   ProductType updateProductType(@PathVariable Long id, @RequestBody @Valid ProductType product) {
      return productTypeService.update(id, product);
   }
   
   @DeleteMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   void deleteProductType(@PathVariable Long id) {
      productTypeService.delete(id);
   }
}
