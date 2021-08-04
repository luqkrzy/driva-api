package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.LessonNotFoundException;
import com.driva.drivaapi.mapper.LessonMapper;
import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.repository.LessonRepository;
import com.driva.drivaapi.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
   
   private final LessonRepository lessonRepository;
   private final LessonMapper lessonMapper;
   
   @Override
   public List<Lesson> findAll() {
	  return lessonRepository.findAll();
   }
   
   @Override
   public Lesson find(Long id) {
	  return lessonRepository.findById(id).orElseThrow(
			  () -> new LessonNotFoundException("Lesson does not exist, id: " + id));
   }
   
   @Override
   public LessonDTO save(LessonDTO lesson) {
	  final Lesson lessonEntity = lessonRepository.save(lessonMapper.lessonDTOtoEntity(lesson));
	  return lessonMapper.entityToLessonDTO(lessonEntity);
   }
   
   @Override
   public Lesson update(Lesson lesson) {
	  return lessonRepository.save(lesson);
   }
   
   @Override
   public void delete(Long id) {
	  final Lesson lesson = find(id);
	  lessonRepository.delete(lesson);
   }
}
