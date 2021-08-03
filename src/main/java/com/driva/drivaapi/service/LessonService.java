package com.driva.drivaapi.service;

import com.driva.drivaapi.model.lesson.Lesson;

import java.util.List;

public interface LessonService {
   List<Lesson> findAll();
   
   Lesson find(Long id);
   
   Lesson save(Lesson lesson);
   
   Lesson update(Lesson lesson);
   
   void delete(Long id);
}
