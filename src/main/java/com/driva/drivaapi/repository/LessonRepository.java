package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
   
   List<Lesson> findByProductId_Id(Long productId);
   
   List<Lesson> findByInstructorId_Id(Long instructorID);
   
   List<Lesson> findAllByProductId_Id(Long productId);
}
