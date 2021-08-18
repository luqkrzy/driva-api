package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.mapper.dto.StudentProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
   
//   private final LessonMapper lessonMapper;
   
   public StudentProductDTO entityToStudentProductDTO(Product product) {
	  return new StudentProductDTO(product);
   }
   
   public List<StudentProductDTO> entitiesToStudentProductDTOs(List<Product> products) {
	  if (products == null) {
		 return null;
	  } return products.stream().filter(Objects::nonNull).map(this::entityToStudentProductDTO)
					   .collect(Collectors.toList());
   }
   
   public List<ProductDTO> entitiesToProductDTOs(List<Product> products) {
	  if (products == null) {
		 return null;
	  } return products.stream().filter(Objects::nonNull).map(this::entityToProductDTO)
					   .collect(Collectors.toList());
   }
   
   public Product studentProductDTOtoEntity(StudentProductDTO studentProductDTO, Student student,
											ProductType productType) {
	  return Product.builder()
					.productType(productType).studentId(student).hoursLeft(productType.getLessonsHours())
					.bookOnline(studentProductDTO.getBookOnline()).isPaid(studentProductDTO.getIsPaid()).price(
					  studentProductDTO.getPrice())
					.build();
   }
   
   public ProductDTO entityToProductDTO(Product product) {
	  final ProductDTO productDTO = new ProductDTO(product);
	  //	  final List<Lesson> lessons = product.getLessons();
	  //	  if (lessons != null) {
	  //		 final List<StudentLesson> studentLessons = lessonMapper.entitiesToStudentLessonDTOs(lessons);
	  //		 productDTO.setLessons(studentLessons);
	  //	  }
	  return productDTO;
   }
}
