package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
   
   private Long id;
   private Long studentId;
   private String studentFullName;
   private Integer hoursLeft;
   private Boolean bookOnline;
   private Boolean isPaid;
   private Double price;
   
   @NotNull(message = "product type id can't be null")
   @Positive(message = "product type must be positive digit")
   private Long productTypeId;
   private String productTypeName;
   private String productTypeDescription;
   private String productTypeCategory;
   private Double productTypeBasePrice;
   private Integer productTypeLessonsHours;
   
   public ProductDTO(Product product) {
	  this.id = product.getId();
	  this.studentId = product.getStudentId().getId();
	  this.studentFullName = product.getStudentId().getFirstName() + ' ' + product.getStudentId().getLastName();
	  this.hoursLeft = product.getHoursLeft();
	  this.bookOnline = product.getBookOnline();
	  this.isPaid = product.getIsPaid();
	  this.price = product.getPrice();
	  this.productTypeId = product.getProductType().getId();
	  this.productTypeName = product.getProductType().getName();
	  this.productTypeDescription = product.getProductType().getDescription();
	  this.productTypeCategory = product.getProductType().getProductCategory();
	  this.productTypeBasePrice = product.getProductType().getBasePrice();
	  this.productTypeLessonsHours = product.getProductType().getLessonsHours();
   }
}
