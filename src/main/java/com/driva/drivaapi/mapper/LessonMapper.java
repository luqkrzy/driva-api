package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.model.user.pojo.GeneralLesson;
import com.driva.drivaapi.model.user.pojo.InstructorInfo;
import com.driva.drivaapi.model.user.pojo.StudentInfo;
import com.driva.drivaapi.model.user.pojo.StudentLesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
   
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
   
	  final LessonDTO lessonDTO = new LessonDTO(lesson);
	  if (instructor != null) {
		 final InstructorInfo instructorInfo = new InstructorInfo(instructor);
		 lessonDTO.setInstructorInfo(instructorInfo);
	  }
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
   
   public GeneralLesson entityToGeneralLesson(Lesson lesson) {
	  final Student student = lesson.getProductId().getStudentId();
	  final Instructor instructor = lesson.getInstructorId();
	  final StudentInfo studentInfo = new StudentInfo(student);
	  final GeneralLesson generalLesson = new GeneralLesson(lesson, studentInfo);
	  if (instructor != null) {
		 final InstructorInfo instructorInfo = new InstructorInfo(instructor);
		 generalLesson.setInstructorInfo(instructorInfo);
	  }
	  return generalLesson;
   }
   
   public List<GeneralLesson> entitiesToGeneralLessons(List<Lesson> lessons) {
	  return lessons.stream().filter(Objects::nonNull).map(this::entityToGeneralLesson)
					.collect(Collectors.toList());
   }
   
   //   public Lesson updateLesson(LessonDTO lessonDTO, User lesson) {
   //	  return lesson.updateUser(lessonDTO);
   //   }
   
   //   public LessonMapper(ProductService productService) {
   //	  this.productService = productService;
   //   }
}
