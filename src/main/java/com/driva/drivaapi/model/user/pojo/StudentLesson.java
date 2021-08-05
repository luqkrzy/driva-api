package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.user.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentLesson {
   
   private Long lessonId;
   private Long productId;
   private String date;
   private String timeStart;
   private String timeEnd;
   private Long instructorId;
   private String instructorFullName;
   private String instructorEmail;
   private String instructorPhone;
   
   public StudentLesson(Lesson lesson, Instructor instructor) {
      this.lessonId = lesson.getId();
      this.productId = lesson.getProductId().getId();
      this.date = lesson.getDate();
      this.timeStart = lesson.getTimeStart();
      this.timeEnd = lesson.getTimeEnd();
      this.instructorId = instructor.getId();
      this.instructorEmail = instructor.getEmail();
      this.instructorFullName = instructor.getFirstName() + ' ' + instructor.getLastName();
      this.instructorPhone = instructor.getPhoneNumber().toString();
   }
}
