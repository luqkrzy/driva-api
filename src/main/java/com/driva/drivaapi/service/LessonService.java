package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.pojo.GeneralLesson;
import com.driva.drivaapi.model.user.pojo.StudentLesson;

import java.util.List;

public interface LessonService {
   List<LessonDTO> findAll();
   
   Lesson find(Long id);
   
   LessonDTO findToLessonDTO(Long id);
   
   GeneralLesson findToGeneralLesson(Long id);
   
   List<LessonDTO> findLessonsByInstructorId(Long id);
   
   LessonDTO save(LessonDTO lesson, Product product, Instructor instructor);
   
   LessonDTO update(Long id, LessonDTO lessonDTO, Instructor instructor);
   
   void delete(Long id);
   
   List<LessonDTO> findByProductId(Long productId);
   
   List<StudentLesson> findByProductIdToStudentLesson(Long productId);
   
   List<GeneralLesson> findAllToGeneralLessons();
   
   List<GeneralLesson> findAllLessonsByProductId(Long id);
}
