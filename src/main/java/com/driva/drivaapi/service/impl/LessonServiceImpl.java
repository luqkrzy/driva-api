package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.LessonNotFoundException;
import com.driva.drivaapi.mapper.LessonMapper;
import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.pojo.GeneralLesson;
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
   public Lesson find(Long id) {
	  return lessonRepository.findById(id).orElseThrow(
			  () -> new LessonNotFoundException("Lesson does not exist, id: " + id));
   }
   
   @Override
   public LessonDTO findToLessonDTO(Long id) {
	  return lessonMapper.entityToLessonDTO(find(id));
   }
   
   @Override
   public GeneralLesson findToGeneralLesson(Long id) {
	  final Lesson lesson = find(id);
	  return lessonMapper.entityToGeneralLesson(lesson);
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
   public LessonDTO update(Long id, LessonDTO lessonDTO, Instructor instructor) {
	  final Lesson lesson = find(id);
	  Lesson updatedLesson = lesson.update(lessonDTO);
	  updatedLesson.setInstructorId(instructor);
	  final Lesson save = lessonRepository.save(updatedLesson);
	  return lessonMapper.entityToLessonDTO(save);
   }
   
   @Override
   public void delete(Long id) {
	  final Lesson lesson = find(id);
	  lessonRepository.delete(lesson);
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
   
   @Override
   public List<GeneralLesson> findAllToGeneralLessons() {
	  final List<Lesson> lessons = lessonRepository.findAll();
	  return lessonMapper.entitiesToGeneralLessons(lessons);
   }
   
   @Override
   public List<GeneralLesson> findAllLessonsByProductId(Long id) {
	  final List<Lesson> lessons = lessonRepository.findAllByProductId_Id(id);
	  return lessonMapper.entitiesToGeneralLessons(lessons);
   }
}
