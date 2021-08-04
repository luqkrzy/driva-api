package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;

import java.util.List;

public interface LessonService {
   List<LessonDTO> findAll();
   
   LessonDTO find(Long id);
   
   LessonDTO save(LessonDTO lesson);
   
   Lesson update(Lesson lesson);
   
   void delete(Long id);
   
   List<LessonDTO> findByProductId(Long productId);
}
