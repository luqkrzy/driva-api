package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.model.lesson.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GeneralLesson {
   
   private Long lessonId;
   private Long productId;
   private LocalDate date;
   private Integer timeStart;
   private Integer hoursCount;
   private StudentInfo studentInfo;
   private InstructorInfo instructorInfo;
   
   public GeneralLesson(Lesson lesson, StudentInfo studentInfo, InstructorInfo instructorInfo) {
      this.lessonId = lesson.getId();
      this.productId = lesson.getProductId().getId();
      this.date = lesson.getDate();
      this.timeStart = lesson.getTimeStart();
      this.hoursCount = lesson.getHoursCount();
      this.studentInfo = studentInfo;
      this.instructorInfo = instructorInfo;
   }
}
