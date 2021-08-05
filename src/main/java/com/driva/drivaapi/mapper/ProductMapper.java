package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.mapper.dto.StudentProductDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.model.user.pojo.StudentLesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
   
   private final LessonMapper lessonMapper;
   
   public StudentProductDTO entityToStudentProductDTO(Product product) {
	  return new StudentProductDTO(product);
   }
   
   public List<StudentProductDTO> entitiesToProductDTOs(List<Product> products) {
	  if (products == null) {
		 return null;
	  } return products.stream().filter(Objects::nonNull).map(this::entityToStudentProductDTO)
					   .collect(Collectors.toList());
   }
   
   //   public List<Product> productDTOsToEntities(List<ProductDTO> productDTOs, Long studentId) {
   //	  if (productDTOs == null) {
   //		 return null;
   //	  }
   //	  return productDTOs.stream().map(pDTO -> productDTOtoEntity(studentId, pDTO)).collect(Collectors.toList());
   //   }
   
   public Product studentProductDTOtoEntity(StudentProductDTO studentProductDTO, Student student,
											ProductType productType) {
	  return Product.builder()
					.productType(productType).studentId(student).hoursLeft(studentProductDTO.getHoursLeft())
					.bookOnline(studentProductDTO.getBookOnline()).isPaid(studentProductDTO.getIsPaid()).price(
					  studentProductDTO.getPrice())
					.build();
   }
   
   public ProductDTO entityToProductDTO(Product product) {
	  final ProductDTO productDTO = new ProductDTO(product);
	  final List<Lesson> lessons = product.getLessons();
	  final List<StudentLesson> studentLessons = lessonMapper.entitiesToStudentLessonDTOs(lessons);
	  productDTO.setLessons(studentLessons);
	  return productDTO;
   }
}
