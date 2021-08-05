package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.pojo.StudentLesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
   
   private Long id;
   private Long productTypeId;
   private Long studentId;
   private String studentFullName;
   private String productTypeName;
   private Integer hoursLeft;
   private Boolean bookOnline;
   private Boolean isPaid;
   private Double price;
   
   private List<StudentLesson> lessons;
   
   public ProductDTO(Product product) {
	  this.id = product.getId();
	  this.productTypeId = product.getProductType().getId();
	  this.studentId = product.getStudentId().getId();
	  this.studentFullName = product.getStudentId().getFirstName() + ' ' + product.getStudentId().getLastName();
	  this.productTypeName = product.getProductType().getName();
	  this.hoursLeft = product.getHoursLeft();
	  this.bookOnline = product.getBookOnline();
	  this.isPaid = product.getIsPaid();
	  this.price = product.getPrice();
   }
}
