package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.InstructorInfo;
import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.mapper.dto.UserDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.service.ProductService;
import com.driva.drivaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
   
   private final ProductService productService;
   private final UserService userService;
   
   public LessonDTO entityToLessonDTO(Lesson lesson) {
	  final UserDTO instructor = userService.findById(lesson.getInstructorId());
	  final InstructorInfo instructorInfo = InstructorInfo.builder()
														  .fistName(instructor.getFirstName())
														  .lastName(instructor.getLastName())
														  .phoneNumber(instructor.getPhoneNumber())
														  .email(instructor.getEmail())
														  .build();
	  final LessonDTO lessonDTO = new LessonDTO(lesson);
	  lessonDTO.setInstructorInfo(instructorInfo);
	  return lessonDTO;
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
