package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.LessonNotFoundException;
import com.driva.drivaapi.mapper.LessonMapper;
import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.pojo.StudentLesson;
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
   public List<LessonDTO> findAll() {
	  final List<Lesson> lessons = lessonRepository.findAll();
	  return lessonMapper.entitiesToLessonDTOs(lessons);
   }
   
   @Override
   public LessonDTO find(Long id) {
	  final Lesson lesson = lessonRepository.findById(id).orElseThrow(
			  () -> new LessonNotFoundException("Lesson does not exist, id: " + id));
	  return lessonMapper.entityToLessonDTO(lesson);
   }
   
   @Override
   public List<LessonDTO> findLessonsByInstructorId(Long id) {
	  final List<Lesson> lessons = lessonRepository.findByInstructorId_Id(id);
	  return lessonMapper.entitiesToLessonDTOs(lessons);
   }
   
   @Override
   public LessonDTO save(LessonDTO lessonDTO, Product product, Instructor instructor) {
	  final Lesson lesson = lessonMapper.lessonDTOtoEntity(lessonDTO, product, instructor);
	  final Lesson savedLesson = lessonRepository.save(lesson);
	  return lessonMapper.entityToLessonDTO(savedLesson);
   }
   
   @Override
   public Lesson update(Lesson lesson) {
	  return lessonRepository.save(lesson);
   }
   
   @Override
   public void delete(Long id) {
	  lessonRepository.deleteById(id);
   }
   
   @Override
   public List<LessonDTO> findByProductId(Long productId) {
	  List<Lesson> lessons = lessonRepository.findByProductId_Id(productId);
	  return lessonMapper.entitiesToLessonDTOs(lessons);
   }
   
   public List<StudentLesson> findByProductIdToStudentLesson(Long productId) {
	  List<Lesson> lessons = lessonRepository.findByProductId_Id(productId);
	  return lessonMapper.entitiesToStudentLessonDTOs(lessons);
   }
}
