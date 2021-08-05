package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.LessonStudentDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;

import java.util.List;

public interface LessonService {
   List<LessonStudentDTO> findAll();
   
   LessonStudentDTO find(Long id);
   
   List<LessonStudentDTO> findLessonsByInstructorId(Long id);
   
   LessonStudentDTO save(LessonStudentDTO lesson, Product product, Instructor instructor);
   
   Lesson update(Lesson lesson);
   
   void delete(Long id);
   
   List<LessonStudentDTO> findByProductId(Long productId);
}
