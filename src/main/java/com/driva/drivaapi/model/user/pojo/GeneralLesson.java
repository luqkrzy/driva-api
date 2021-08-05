package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.model.lesson.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GeneralLesson {
   
   private Long lessonId;
   private Long productId;
   private String date;
   private String timeStart;
   private String timeEnd;
   private StudentInfo studentInfo;
   private InstructorInfo instructorInfo;
   
   public GeneralLesson(Lesson lesson, StudentInfo studentInfo, InstructorInfo instructorInfo) {
      this.lessonId = lesson.getId();
      this.productId = lesson.getProductId().getId();
      this.date = lesson.getDate();
      this.timeStart = lesson.getTimeStart();
      this.timeEnd = lesson.getTimeEnd();
      this.studentInfo = studentInfo;
      this.instructorInfo = instructorInfo;
   }
}
