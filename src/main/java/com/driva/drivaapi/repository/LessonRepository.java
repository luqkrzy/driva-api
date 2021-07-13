package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
