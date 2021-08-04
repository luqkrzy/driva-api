package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
   
   private final ProductService productService;
   
   public LessonDTO entityToLessonDTO(Lesson lesson) {
	  return new LessonDTO(lesson);
   }
   
   public List<LessonDTO> entitiesToLessonDTOs(List<Lesson> lessons) {
	  return lessons.stream().filter(Objects::nonNull).map(this::entityToLessonDTO).collect(Collectors.toList());
   }
   
   public Lesson lessonDTOtoEntity(LessonDTO lessonDTO) {
	  final Product product = productService.find(lessonDTO.getProductId());
	  final Lesson lesson = new Lesson(lessonDTO);
	  lesson.setProductId(product);
	  return lesson;
   }
   
   //   public Lesson updateLesson(LessonDTO lessonDTO, User lesson) {
   //	  return lesson.updateUser(lessonDTO);
   //   }
   
   //   public LessonMapper(ProductService productService) {
   //	  this.productService = productService;
   //   }
}
