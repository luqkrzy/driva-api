package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.user.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentLesson {
   
   private Long lessonId;
   private Long productId;
   private LocalDate date;
   private Integer timeStart;
   private Integer hoursCount;
   private Long instructorId;
   private String instructorFullName;
   private String instructorEmail;
   private String instructorPhone;
   
   public StudentLesson(Lesson lesson, Instructor instructor) {
	  this.lessonId = lesson.getId();
	  this.productId = lesson.getProductId().getId();
	  this.date = lesson.getDate();
	  this.timeStart = lesson.getTimeStart();
	  this.hoursCount = lesson.getHoursCount();
	  if (instructor != null) {
		 this.instructorId = instructor.getId();
		 this.instructorEmail = instructor.getEmail();
		 this.instructorFullName = instructor.getFirstName() + ' ' + instructor.getLastName();
		 this.instructorPhone = Integer.toString(instructor.getPhoneNumber());
	  }
   }
}
