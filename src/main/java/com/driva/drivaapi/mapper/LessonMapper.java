package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.model.user.pojo.GeneralLesson;
import com.driva.drivaapi.model.user.pojo.StudentLesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
   
   public LessonDTO entityToLessonDTO(Lesson lesson) {
	  final Instructor instructor = lesson.getInstructorId();
   
	  final LessonDTO lessonDTO = new LessonDTO(lesson);
	  if (instructor != null) {
		 lessonDTO.setInstructor(instructor);
	  }
	  return lessonDTO;
   }
   
   public List<LessonDTO> entitiesToLessonDTOs(List<Lesson> lessons) {
	  return lessons.stream().filter(Objects::nonNull).map(this::entityToLessonDTO)
					.collect(Collectors.toList());
   }
   
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
	  final GeneralLesson generalLesson = new GeneralLesson(lesson);
	  if (student != null) {
		 generalLesson.setStudent(student);
	  }
   
	  if (instructor != null) {
		 generalLesson.setInstructor(instructor);
	  }
	  return generalLesson;
   }
   
   public List<GeneralLesson> entitiesToGeneralLessons(List<Lesson> lessons) {
	  return lessons.stream().filter(Objects::nonNull).map(this::entityToGeneralLesson)
					.collect(Collectors.toList());
   }
}
