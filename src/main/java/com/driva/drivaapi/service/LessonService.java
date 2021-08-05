package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.pojo.StudentLesson;

import java.util.List;

public interface LessonService {
   List<LessonDTO> findAll();
   
   LessonDTO find(Long id);
   
   List<LessonDTO> findLessonsByInstructorId(Long id);
   
   LessonDTO save(LessonDTO lesson, Product product, Instructor instructor);
   
   Lesson update(Lesson lesson);
   
   void delete(Long id);
   
   List<LessonDTO> findByProductId(Long productId);
   
   List<StudentLesson> findByProductIdToStudentLesson(Long productId);
}
