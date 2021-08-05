package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.pojo.InstructorInfo;
import com.driva.drivaapi.model.user.pojo.StudentLesson;
import com.driva.drivaapi.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
   
   private final InstructorRepository instructorRepository;
   
   //   public LessonDTO entityToLessonDTO(Lesson lesson) {
   //
   //	  final Instructor instructor = instructorRepository.findById(lesson.getInstructorId()).orElseThrow(
   //			  () -> new InstrucorNotFoundException("Instructor not found, id: " + lesson.getInstructorId()));
   //
   //	  final InstructorInfo instructorInfo = InstructorInfo.builder()
   //														  .fistName(instructor.getFirstName())
   //														  .lastName(instructor.getLastName())
   //														  .phoneNumber(Integer.toString(instructor.getPhoneNumber()))
   //														  .email(instructor.getEmail())
   //														  .build();
   //	  final LessonDTO lessonDTO = new LessonDTO(lesson);
   //
   //	  lessonDTO.setInstructorInfo(instructorInfo);
   //	  return lessonDTO;
   //   }
   
   public LessonDTO entityToLessonDTO(Lesson lesson) {
	  final Instructor instructor = lesson.getInstructorId();
	  final InstructorInfo instructorInfo = new InstructorInfo(instructor);
	  final LessonDTO lessonDTO = new LessonDTO(lesson);
	  lessonDTO.setInstructorInfo(instructorInfo);
	  return lessonDTO;
   }
   
   public List<LessonDTO> entitiesToLessonDTOs(List<Lesson> lessons) {
	  return lessons.stream().filter(Objects::nonNull).map(this::entityToLessonDTO)
					.collect(Collectors.toList());
   }
   
   //   public Lesson lessonDTOtoEntity(LessonDTO lessonDTO) {
   //	  final Product product = productService.find(lessonDTO.getProductId());
   //	  final Lesson lesson = new Lesson(lessonDTO);
   //	  lesson.setProductId(product);
   //	  return lesson;
   //   }
   
   public Lesson lessonDTOtoEntity(LessonDTO lessonDTO, Product product, Instructor instructor) {
	  final Lesson lesson = new Lesson(lessonDTO);
	  lesson.setProductId(product);
	  lesson.setInstructorId(instructor);
	  return lesson;
   }
   
   public List<StudentLesson> entitiesToStudentLessonDTOs(List<Lesson> lessons) {
	  return lessons.stream().filter(Objects::nonNull).map(this::entityToStudentLessonDTO)
					.collect(Collectors.toList());
   }
   
   public StudentLesson entityToStudentLessonDTO(Lesson lesson) {
	  final Instructor instructor = lesson.getInstructorId();
	  return new StudentLesson(lesson, instructor);
   }
   
   //   public Lesson updateLesson(LessonDTO lessonDTO, User lesson) {
   //	  return lesson.updateUser(lessonDTO);
   //   }
   
   //   public LessonMapper(ProductService productService) {
   //	  this.productService = productService;
   //   }
}
